package com.ruoyi.web.core.config;

import com.ruoyi.detection.mapper.InsertData;
import com.ruoyi.system.service.INumberService;
import io.swagger.models.auth.In;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author liukun
 * @date 2021/9/2
 */
@SpringBootTest
class SwaggerConfigTest {

    @Resource
    private InsertData insertData;

    @Autowired
    private INumberService numberService;

    @Test
    void createRestApi() {
        List<Long> byIDS = insertData.findByIDS();
        List<Long> inspectors = insertData.findInspectors();
        int size = inspectors.size();
        Random random = new Random();
        for (Long itemID : byIDS) {
            int num = random.nextInt(size);
            int idx = 0;
            for(Long inspector : inspectors){
                Integer flag = idx++ == num ? 2 : 1;
                insertData.insertIntoDetectionList(inspector,itemID,1l,flag);
            }
        }
    }

    @Test
    public void testInsertToMethodsAndItems(){
        List<Long> itemList = insertData.findByIDS();
        List<Long> methods = insertData.findMethods();
        int size = methods.size();
        Random random = new Random();
        for(Long itemId : itemList){
            int num = random.nextInt(size);
            int idx = 0;
            for(Long method : methods){
                Integer flag = idx++ == num ? 2 : 1;
                insertData.insertIntoMethodsAndItemList(itemId,method,1l,flag);
            }
        }
    }

    @Test
    public void testNumberService(){
        List<String> strings = numberService.generateReportNumber(2);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    public static void main(String[] args) {
        System.out.println(lastRemaining(5, 3));
        System.out.println(last(5, 3));
    }
    public static int lastRemaining(int n, int m) {
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < n; ++i){
            list.add(i);
        }
        int idx = 0;
        while(list.size() != 1){
            if((idx+1)%m == 0){
                list.remove((idx)%list.size());
            }
            //
            idx++;
        }
        return list.get(0);
    }
    private static int last(int n, int m){
        if(n == 1) return 0;
        return (last(n-1,m)+m)%n;
    }
    // 碎了的话，那么说明还可以
    // [1,mid-1][mid,r]
}
