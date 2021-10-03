package com.ruoyi.limit.mapper;

import com.ruoyi.limit.domain.Food;
import com.ruoyi.limit.domain.Standard;
import com.ruoyi.limit.domain.vo.LimitInfoVo;
import com.ruoyi.limit.domain.Pesticide;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 限量库详细信息Mapper接口
 * 
 * @author wlf
 * @date 2021-07-21
 */
public interface LimitInfoVoMapper
{
    /**
     * 查询限量库详细信息列表
     * 多表联合查询
     * @return 限量库详细信息集合
     */
    public List<LimitInfoVo> selectLimitInfoVoList(LimitInfoVo limitInfoVo);

    /**
     * 根据食品查询限量库详细信息集合
     *
     */
    public List<LimitInfoVo> selectLimitInfoVoListByFood(Food food);

    /**
     * 根据农药查询限量库详细信息集合
     *
     */
    public List<LimitInfoVo> selectLimitInfoVoListByPesticide(Pesticide pesticide);

    /**
     * 根据检测标准编号查询限量库详细信息集合 Long standard
     */
    public List<LimitInfoVo> selectLimitInfoVoListByStandard(Standard standard);


    /**
     * 根据检测标准id 删除限量库详细信息
     */
    public int deleteLimitInfoVoListByStandards(@Param("standardIDs") Long[] standardIDs, @Param("detectionCenterID") Long detectionCenterID);
}
