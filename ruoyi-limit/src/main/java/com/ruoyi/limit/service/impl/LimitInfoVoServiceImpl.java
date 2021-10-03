package com.ruoyi.limit.service.impl;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.CustomException;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.limit.domain.*;
import com.ruoyi.limit.domain.vo.LimitInfoVo;
import com.ruoyi.limit.mapper.*;
import com.ruoyi.limit.service.ILimitInfoVoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 限量库详细信息Service业务层处理
 * 
 * @author lkun
 * @date 2021-07-21
 */
@Service
public class LimitInfoVoServiceImpl implements ILimitInfoVoService
{
    private static final Logger log = LoggerFactory.getLogger(LimitInfoVoServiceImpl.class);
    @Resource
    private LimitInfoVoMapper limitInfoVoMapper;
    @Resource
    private DetectionStandardFoodPesticideMapper detectionStandardFoodPesticideMapper;
    @Resource
    private FoodTypeMapper foodTypeMapper;

    @Resource
    private DetectionMethodMapper detectionMethodMapper;

    @Resource
    private FoodMapper foodMapper;

    @Resource
    private PesticideMapper pesticideMapper;

    @Resource
    private SamplePesticideMapper samplePesticideMapper;

    @Resource
    private PesticideDetectionMethodMapper pesticideDetectionMethodMapper;

    @Resource
    private DetectionMethodStandardMapper detectionMethodStandardMapper;

    @Resource
    private StandardMapper standardMapper;

    @Resource
    private FoodServiceImpl foodService;

    /**
     * 构建前端所需要的下拉树结构
     * @param foodsType 食品类别
     * @return 树结构列表
     */
    public List<FoodTreeSelect> buildFoodTypeTreeSelect(List<FoodType> foodsType) {
        List<FoodType> foodTypeTrees = buildFoodTree(foodsType);
        return foodTypeTrees.stream().map(FoodTreeSelect::new).collect(Collectors.toList());
    }

    /**
     * 导入限量库详细信息
     *
     * @param limitInfoVoList 限量库数据列表
     * @param isUpdateSupport 是否更新支持，如果已存在，则进行更新数据
     * @return 操作结果
     */
    @Override
    public String importLimit(List<LimitInfoVo> limitInfoVoList, boolean isUpdateSupport) {
        foodTypeStrMap.clear();
        PesticideDetectionMethodMap.clear();
        StandardDetectionMethodMap.clear();
        if (StringUtils.isNull(limitInfoVoList) || limitInfoVoList.size() == 0)
        {
            throw new CustomException("导入基础库信息，数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();

        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();

        //准备食品 农药 检测方法 id和名称对应的map
        //foodName---foodTypeID
//        Map<String,Food> foodMap = foodMapper.selectFoodIDNameMap();
        //foodTypeName---foodTypeID  SPLB_MC---SPLB_ID
        Map<String, FoodType> foodTypeMap = foodTypeMapper.selectFoodTypeNameMap();
        //pesticideName---pesticideID
        Map<String, Pesticide> pesticideMap = pesticideMapper.selectPesticideIDNameMap(detectionCenterID);
        //detectionNames---detectionFunctions(ID)
        Map<String, DetectionMethod> detectionMethodMap = detectionMethodMapper.selectDetectionMethodNameMap();
        //standard---standardNumber
        Map<String, Standard> standardMap = standardMapper.selectStandardIDNameMap(detectionCenterID);
        //检测方法-检测项目
//        Map<Long, PesticideDetectionMethod> pesticideDetectionMethodMap = pesticideDetectionMethodMapper.selectPesticideDetectionMethodMap();

        int row = 1;
        for(LimitInfoVo limitInfoVo : limitInfoVoList)
        {
            try
            {
                row++;
                //判断该行是否包含四列数据
                if(limitInfoVo.getStandardNumber().equals("") || limitInfoVo.getFoodName().equals("") || limitInfoVo.getPesticideName().equals("")){
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行存在空值");
                    continue;
                }
                //判断是否含该检测标准编号
                if(standardMap.containsKey(limitInfoVo.getStandardNumber())) {
                    limitInfoVo.setStandard(standardMap.get(limitInfoVo.getStandardNumber()).getStandard());
                    boolean flag = true;
                    int ret = 0;
                    //食品类别层级处理
                    ret = handleFoodTypeString(limitInfoVo, limitInfoVo.getFoodTypeStr(), foodTypeMap, isUpdateSupport);
                    if(ret != 0)
                    {
                        if(ret == -1)
                        {
                            failureNum++;
                            failureMsg.append("<br/>" + "Excel表中第" + row + "行数据" + "<br/>&nbsp;&nbsp;"+ "食品类别层级和食品名称之间有冲突！");
                            continue;
                        }
                        else // ret == -2
                        {
                            failureNum++;
                            failureMsg.append("<br/>" + "Excel表中第" + row + "行数据" + "<br/>&nbsp;&nbsp;"+ limitInfoVo.getFoodName()+ "信息发生变化！如需更新，请点击更新按钮。");
                        }
                    }

                    //检测农药处理
                    flag = handlePesticide(limitInfoVo, pesticideMap, isUpdateSupport);
                    if(!flag)
                    {
                        failureNum++;
                        failureMsg.append("<br/>" + "Excel表中第" + row + "行数据" + "<br/>&nbsp;&nbsp;"+ limitInfoVo.getPesticideName()+ "信息发生变化！如需更新，请点击更新按钮。");
                    }
                    //检测方法处理 检测方法-检测项目 检测方法-检测标准
                    handleFunctions(limitInfoVo,detectionMethodMap);

                    //处理食品-检测农药
                    handleFoodPesticide(limitInfoVo);

                    DetectionStandardFoodPesticide detectionStandardFoodPesticide = new DetectionStandardFoodPesticide(limitInfoVo.getStandard(),
                                            limitInfoVo.getFoodTypeID(), limitInfoVo.getPesticideID(),
                                    limitInfoVo.getDetectionFunctionIDs(),limitInfoVo.getMaxResidualAmount(),limitInfoVo.getRemark());
                    DetectionStandardFoodPesticide IsExist = detectionStandardFoodPesticideMapper.selectLimitInfoVoListByIDs(detectionStandardFoodPesticide);
                    if (StringUtils.isNull((IsExist)))
                    {
                        //不存在 插入jcbz_sp_ny_jcff表
                        detectionStandardFoodPesticide.setDetectionCenterID(detectionCenterID);
                        detectionStandardFoodPesticide.setIsValid(1);
                        detectionStandardFoodPesticideMapper.insertLimitInfoVo(detectionStandardFoodPesticide);
                        successNum++;
                    }
                    else if(isUpdateSupport)
                    {
                        //存在 更新jcbz_sp_ny_jcff表
                        detectionStandardFoodPesticideMapper.updateLimitInfoVo(detectionStandardFoodPesticide);
                        successNum++;
                    }
                    else
                    {
                        failureNum++;
                        failureMsg.append("<br/>" + "Excel表中第" + row + "行数据已存在！如需更新，请点击更新按钮。");
                    }
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + "Excel表中第" + row + "行数据出错" + "<br/>"+"检测标准表中不存在检测编号为：" + limitInfoVo.getStandardNumber()+ "的记录，请先添加该检测标准！！！");
                    continue;
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + "Excel表中第" + row + "行数据出错";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }

        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new CustomException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条");
        }

        return successMsg.toString();
    }

    /**
     * 处理食品类别层级
     * 以 / 分割
     * 例如 谷物/麦类/小麦
     * return -1 食品类别层级出错
     */
    Map<String, Long> foodTypeStrMap = new HashMap<>();
    public int handleFoodTypeString(LimitInfoVo limitInfoVo, String foodTypesStr, Map<String, FoodType> foodTypeMap, boolean isUpdateSupport){
        String[] foodTypeList = foodTypesStr.split("/");
        if(!foodTypeList[foodTypeList.length-1].equals(limitInfoVo.getFoodName()))
        {
            return -1;
        }
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        String TypeStr = foodTypesStr.substring(0,foodTypesStr.lastIndexOf("/"));
        System.out.println("===TypeStr==="+TypeStr);
        int i = 0;
        if(foodTypeStrMap.containsValue(TypeStr))
        {
            i = foodTypeList.length ;
            FoodType foodTypeInsert = new FoodType(foodTypeStrMap.get(TypeStr), foodTypeList[foodTypeList.length - 1]);
            foodTypeInsert.setIsValid(1);
            foodTypeInsert.setDetectionCenterID(detectionCenterID);
            foodTypeMapper.insertFoodType(foodTypeInsert);
            foodTypeMap.put(foodTypeInsert.getFoodTypeName(), foodTypeInsert);
        }
        for(; i < foodTypeList.length; i++){
            //根节点处理
            if(i == 0)
            {
                if(!foodTypeMap.containsKey(foodTypeList[i]))
                {
                    FoodType foodTypeInsert = new FoodType((long) 0,foodTypeList[i]);
                    foodTypeInsert.setIsValid(1);
                    foodTypeInsert.setDetectionCenterID(detectionCenterID);
                    foodTypeMapper.insertFoodType(foodTypeInsert);
                    foodTypeMap.put(foodTypeInsert.getFoodTypeName(), foodTypeInsert);
                    if(i == foodTypeList.length -2)
                    {
                        foodTypeStrMap.put(TypeStr,foodTypeInsert.getFoodTypeID());
                    }
                }
            }
            else //
            {
                //食品类别父
                FoodType foodTypeParent = foodTypeMap.get(foodTypeList[i-1]);

                FoodType foodTypeInsert = new FoodType(foodTypeParent.getFoodTypeID(), foodTypeList[i]);
                //蔬菜/豆类/荚不可食类/利马豆   谷物/杂粮类/利马豆
                FoodType IsExist = foodTypeMapper.selectFoodTypeExist(foodTypeInsert);
                if(StringUtils.isNull(IsExist))
                {
                    foodTypeInsert.setIsValid(1);
                    foodTypeInsert.setDetectionCenterID(detectionCenterID);
                    foodTypeMapper.insertFoodType(foodTypeInsert);
                    foodTypeMap.put(foodTypeInsert.getFoodTypeName(), foodTypeInsert);
                }
                if(i == foodTypeList.length -2)
                {
                    foodTypeStrMap.put(TypeStr,foodTypeInsert.getFoodTypeID());
                }
            }

        }   //for
        int index = foodTypeList.length - 1;
        // 处理叶子节点， 不存在 需要插入xlk_sp
        //父ID 食品名称
        FoodType foodType = new FoodType(foodTypeMap.get(foodTypeList[index-1]).getFoodTypeID(),foodTypeList[index]);
        long foodTypeID = foodTypeMapper.selectFoodTypeId(foodType);
        Food foodInsert  = new Food(foodTypeID,limitInfoVo.getFoodName(),limitInfoVo.getFoodNickName(),  limitInfoVo.getDetectionPart());
        //是否存在该食品信息 食品ID和食品名称
        Food IsExist = foodMapper.selectFoodExist(foodInsert);
        limitInfoVo.setFoodTypeID(foodInsert.getFoodTypeID());
        if(StringUtils.isNull(IsExist))
        {
            //不存在该食品信息
            foodInsert.setIsValid(1);
            foodInsert.setDetectionCenterID(detectionCenterID);
            foodMapper.insertFood(foodInsert);
        }
        else
        {
            if(isUpdateSupport)
            {
                foodInsert.setDetectionCenterID(detectionCenterID);
                foodMapper.updateFood(foodInsert);
                return 0;
            }
            //存在该食品 检测表中食品信息有无更改 更改会得到空
            foodInsert.setDetectionCenterID(detectionCenterID);
            Food IsChange = foodMapper.selectFoodChange(foodInsert);
            if(StringUtils.isNull(IsChange) && !isUpdateSupport)
            {
                return -2;
            }
        }
        return 0;
    }

    /**
     * 处理检测农药
     */
    public boolean handlePesticide(LimitInfoVo limitInfoVo, Map<String, Pesticide> pesticideMap, boolean isUpdateSupport) {

        Pesticide pesticideInsert = new Pesticide(
                limitInfoVo.getPesticideName(), limitInfoVo.getPesticideEnglishName(),
                limitInfoVo.getPesticideExempt(),limitInfoVo.getAdi(),
                limitInfoVo.getPesticideResidues(), limitInfoVo.getPesticideFunction());
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        pesticideInsert.setDetectionCenterID(detectionCenterID);
        //不存在该农药
        if(!pesticideMap.containsKey(limitInfoVo.getPesticideName()))
        {
            pesticideInsert.setIsValid(1);
            pesticideMapper.insertPesticide(pesticideInsert);
            pesticideMap.put(limitInfoVo.getPesticideName(), pesticideInsert);

            limitInfoVo.setPesticideID(pesticideMap.get(limitInfoVo.getPesticideName()).getPesticideID());
            return true;
        }
        else //存在该农药
        {
            Pesticide IsExist = pesticideMapper.selectPesticideExist(pesticideInsert);
            limitInfoVo.setPesticideID(pesticideMap.get(limitInfoVo.getPesticideName()).getPesticideID());
            if(StringUtils.isNull(IsExist))
            {
                if(isUpdateSupport)
                {
                    //更新
                    pesticideMapper.updatePesticide(pesticideInsert);
                    //修改map
                    pesticideMap.remove(limitInfoVo.getPesticideName());
                    pesticideMap.put(limitInfoVo.getPesticideName(), pesticideInsert);
                    return true;
                }
                return false;
            }
        }
        return true;
    }

    /**
     * 处理检测方法
     * 未插入的检测方法插入检测方法标，并将检测方法转换为检测方法ID的拼接
     * 以 英文逗号 , 分割
     * 例如 GB/T 5009.165,GB/T 5009.175 ---> 1,2
     */
    public void handleFunctions(LimitInfoVo limitInfoVo,Map<String, DetectionMethod> detectionMethodMap){
        if(limitInfoVo.getDetectionNames() == null || limitInfoVo.getDetectionNames().equals("") )
        {
            return;
        }
        //检测方法处理
        String[] detectionNameList = limitInfoVo.getDetectionNames().split(",");
        String strDetectionIDs = "";
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        for(int i = 0; i < detectionNameList.length; i++){
            //检测方法存在
            if(detectionMethodMap.containsKey(detectionNameList[i]))
            {
                strDetectionIDs += detectionMethodMap.get(detectionNameList[i]).getDetectionMethodID() +",";
            }
            else //检测方法不存在
            {
                //插入
                DetectionMethod detectionMethodInsert = new DetectionMethod(detectionNameList[i]);
                detectionMethodInsert.setDetectionCenterID(detectionCenterID);
                detectionMethodInsert.setIsValid(1);
                detectionMethodMapper.insertDetectionMethod(detectionMethodInsert);
                detectionMethodMap.put(detectionNameList[i], detectionMethodInsert);
            }

            this.handlePesticideDetectionMethod(detectionMethodMap.get(detectionNameList[i]).getDetectionMethodID(),limitInfoVo.getPesticideID());
            this.handleStandardDetectionMethod(detectionMethodMap.get(detectionNameList[i]).getDetectionMethodID(),limitInfoVo.getStandard());
        }

        if(strDetectionIDs.length()>1){
            strDetectionIDs = strDetectionIDs.substring(0, strDetectionIDs.length()-1);
            limitInfoVo.setDetectionFunctionIDs(strDetectionIDs);
        }

    }

    /**
     * 处理检测方法和检测项目
     * 未插入的检测方法和检测项目，插入检测方法和检测项目表(xlk_jcxm_jcff)中
     */
    Map<Long,Long> PesticideDetectionMethodMap = new HashMap<>();
    public void handlePesticideDetectionMethod(Long detectionMethodID, Long pesticideID)
    {
        if(PesticideDetectionMethodMap.containsValue(pesticideID)){
            return;
        }
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();

        PesticideDetectionMethod pesticideDetectionMethod = new PesticideDetectionMethod();
        pesticideDetectionMethod.setDetectionMethodID(detectionMethodID);
        pesticideDetectionMethod.setPesticideID(pesticideID);
        pesticideDetectionMethod.setDetectionCenterID(detectionCenterID);
        PesticideDetectionMethod isExist = pesticideDetectionMethodMapper.selectPesticideDetectionMethodExist(pesticideDetectionMethod);
        if(StringUtils.isNull(isExist)){
            pesticideDetectionMethod.setIsValid(1);
            pesticideDetectionMethodMapper.insertPesticideDetectionMethod(pesticideDetectionMethod);
            PesticideDetectionMethodMap.put(pesticideID,detectionMethodID);
        }
    }

    /**
     * 处理样品和检测项目
     * 未插入的样品和检测项目，插入样品和检测项目表(xlk_yp-jcxm)中
     */
    Map<Long,Long> StandardDetectionMethodMap = new HashMap<>();
    public void handleStandardDetectionMethod(Long detectionMethodID, Long standard){
        if(StandardDetectionMethodMap.containsValue(standard))
        {
            return;
        }
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();

        DetectionMethodStandard detectionMethodStandard = new DetectionMethodStandard();
        detectionMethodStandard.setDetectionMethodID(detectionMethodID);
        detectionMethodStandard.setStandardID(standard);
        detectionMethodStandard.setDetectionCenterID(detectionCenterID);
        DetectionMethodStandard isExist = detectionMethodStandardMapper.selectDetectionMethodStandardExist(detectionMethodStandard);
        if (StringUtils.isNull(isExist)){
            detectionMethodStandard.setIsValid(1);
            detectionMethodStandardMapper.insertDetectionMethodStandard(detectionMethodStandard);
            StandardDetectionMethodMap.put(standard,detectionMethodID);
        }
    }
    /**
     * 处理样品和检测项目
     * 未插入的样品和检测项目，插入样品和检测项目表(xlk_yp-jcxm)中
     */
    public void handleFoodPesticide(LimitInfoVo limitInfoVo)
    {
        if(limitInfoVo.getPesticideExempt() == 1)
        {
            return;
        }
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();

        SamplePesticide samplePesticide = new SamplePesticide();
        samplePesticide.setSampleID(limitInfoVo.getFoodTypeID());
        samplePesticide.setPesticideID(limitInfoVo.getPesticideID());
        samplePesticide.setDetectionCenterID(detectionCenterID);
        SamplePesticide isExist = samplePesticideMapper.selectSamplePesticideExist(samplePesticide);
        if(StringUtils.isNull(isExist))
        {
            samplePesticide.setIsValid(1);
            samplePesticideMapper.insertSamplePesticide(samplePesticide);
        }
    }



    /**
     * 查询限量库中食品类别信息
     *
     * @param foodType 某个食品类别
     * @return 限量库中食品类别关系
     */

    public List<FoodType> selectFoodTree(FoodType foodType) {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        foodType.setDetectionCenterID(detectionCenterID);
        return foodTypeMapper.selectFoodTypeList(foodType);
    }

    /**
     * 根据食品查询限量库详细信息列表
     * 
     *
     * @return 限量库详细信息
     */
    @Override
    public List<LimitInfoVo> selectLimitInfoVoList(LimitInfoVo limitInfoVo)
    {
        SysUser user = SecurityUtils.getLoginUser().getUser();
        List<LimitInfoVo> list = limitInfoVoMapper.selectLimitInfoVoList(limitInfoVo);
        handleFunctionIDtoNames(list);
        return list;
    }

    /**
     * 根据食品查询限量库详细信息列表
     * @return 限量库详细信息
     */
    public List<LimitInfoVo> selectLimitInfoVoListByFood(Food food){
        List<LimitInfoVo> list = limitInfoVoMapper.selectLimitInfoVoListByFood(food);
        handleFunctionIDtoNames(list);
        return list;
    }

    /**
     * 根据农药查询限量库详细信息列表
     * @return 限量库详细信息
     */
    public List<LimitInfoVo> selectLimitInfoVoListByPesticide(Pesticide pesticide){
        List<LimitInfoVo> list = limitInfoVoMapper.selectLimitInfoVoListByPesticide(pesticide);
        handleFunctionIDtoNames(list);
        return list;
    }

    /**
     * 根据检测标准查询限量库详细信息列表
     * @return 限量库详细信息
     */
    public List<LimitInfoVo> selectLimitInfoVoListByStandard(Standard standard){
        SysUser user = SecurityUtils.getLoginUser().getUser();
        Long detectionCenterID = user.getDetectionCenterID();
        standard.setDetectionCenterID(detectionCenterID);
        List<LimitInfoVo> limitInfoVoList = limitInfoVoMapper.selectLimitInfoVoListByStandard(standard);

        //detectionIDs---detectionNames
        Map<Long, DetectionMethod> detectionMethodMap = detectionMethodMapper.selectDetectionMethodIDMap();

        //handleFunctionIDtoNames(limitInfoVoList);

        //食品类别ID和食品类别层级Map
        Map<Long,String> foodTypeIDStrMap = new HashMap<Long, String>() ;
        for(LimitInfoVo limitInfoVo : limitInfoVoList)
        {
            //将ID转为方法名称
            handleFunctionID2Names(limitInfoVo,detectionMethodMap);
            if(!foodTypeIDStrMap.containsKey(limitInfoVo.getFoodTypeID()))
            {
                String str = foodService.generateFoodTypeStr(limitInfoVo.getFoodTypeID());
                limitInfoVo.setFoodTypeStr(str);
                foodTypeIDStrMap.put(limitInfoVo.getFoodTypeID(),str);
            }
            else
            {
                limitInfoVo.setFoodTypeStr(foodTypeIDStrMap.get(limitInfoVo.getFoodTypeID()));
            }

        }
        return limitInfoVoList;
    }



    //检测名称转检测ID
    /**
     * 填充检测方法名称字段 检测ID转检测名称　以英文逗号分割
     * @param　
     * @return 填充检测方法的限量库列表
     */
    public void handleFunctionIDtoNames(List<LimitInfoVo> list){
        if(list.size() <= 0)
        {
            return;
        }
        //detectionIDs---detectionNames
        Map<Long, DetectionMethod> detectionMethodMap = detectionMethodMapper.selectDetectionMethodIDMap();

        //检测方法IDs转Names 英文逗号分割
        for (LimitInfoVo limitInfo: list)
        {
            handleFunctionID2Names(limitInfo,detectionMethodMap);
        }

    }

    /**
     * 处理LimitInfo
     * @param limitInfo
     */
    public void handleFunctionID2Names(LimitInfoVo limitInfo, Map<Long, DetectionMethod> detectionMethodMap){

        if(limitInfo.getDetectionFunctionIDs() == null)
        {
            return;
        }

        String[] strDetectionIDs = limitInfo.getDetectionFunctionIDs().split(",");
        String strDetectionNames = "";

        for(String strDetectionID : strDetectionIDs) {
            if (detectionMethodMap.containsKey(Long.parseLong(strDetectionID))) {
                strDetectionNames += detectionMethodMap.get(Long.parseLong(strDetectionID)).getDetectionMethodName() + ",";
            }
        }
        if(strDetectionNames.length() > 0)
        {
            strDetectionNames = strDetectionNames.substring(0, strDetectionNames.length() -1);
            limitInfo.setDetectionNames(strDetectionNames);
        }
    }
    /**
     * 构建前端所需要的食品类别树形结构
     * @param foods 食品类别
     * @return 树结构列表
     */

    public List<FoodType> buildFoodTree(List<FoodType> foods) {
        List<FoodType> returnList = new ArrayList<FoodType>();
        List<Long> tempList = new ArrayList<Long>();
        for (FoodType food : foods)
        {
            tempList.add(food.getFoodTypeID());
        }
        for (Iterator<FoodType> iterator = foods.iterator(); iterator.hasNext();)
        {
            FoodType food = (FoodType) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(food.getFoodTypeParentID()))
            {
                recursionFn(foods, food);
                returnList.add(food);
            }
        }
        if (returnList.isEmpty())
        {
            returnList = foods;
        }
        return returnList;
    }
    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<FoodType> list, FoodType t)
    {
        return getChildList(list, t).size() > 0 ? true : false;
    }
    /**
     * 得到子节点列表
     */
    private List<FoodType> getChildList(List<FoodType> list, FoodType t)
    {
        List<FoodType> tlist = new ArrayList<FoodType>();
        Iterator<FoodType> it = list.iterator();
        while (it.hasNext())
        {
            FoodType n = (FoodType) it.next();
            if (StringUtils.isNotNull(n.getFoodTypeParentID()) && n.getFoodTypeParentID().longValue() == t.getFoodTypeID().longValue())
            {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<FoodType> list, FoodType t)
    {
        // 得到子节点列表
        List<FoodType> childList = getChildList(list, t);
        t.setChildren(childList);
        for (FoodType tChild : childList)
        {
            if (hasChild(list, tChild))
            {
                recursionFn(list, tChild);
            }
        }
    }
}
