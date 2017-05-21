package com.lq.lss.utils;

import com.lq.lss.model.EasyUITreeNew;

import java.util.ArrayList;
import java.util.List;

public class EasyuiUtil {

    /**
     * easyui tree 父节点
     * 
     * @param id 根节点的值
     * @param treeDataList
     * @return
     */
    public final static List<EasyUITreeNew> getfatherNode(String id, List<EasyUITreeNew> treeDataList) {
        List<EasyUITreeNew> newTreeDataList = new ArrayList<EasyUITreeNew>();
        for (EasyUITreeNew jsonTreeData : treeDataList) {
            if (jsonTreeData.getPid().equals(id)) {
                // 获取父节点下的子节点
                List<EasyUITreeNew> childList =  getChildrenNode(jsonTreeData.getId(), treeDataList);
                if(childList.size()>0){
                    jsonTreeData.setChildren(childList);
                    if(jsonTreeData.getChecked() !=null && jsonTreeData.getChecked()){
                        jsonTreeData.setChecked(false); // 重置父节点为未选中，根据子节点状态自动判断
                    }
                }else{
                    jsonTreeData.setState("open");
                }
                
                newTreeDataList.add(jsonTreeData);
            }
        }
        return newTreeDataList;
    }

    /**
     * easyui tree 的子节点
     * 
     * @param pId 根节点
     * @param treeDataList
     * @return
     */
    private final static List<EasyUITreeNew> getChildrenNode(String pId, List<EasyUITreeNew> treeDataList) {
        List<EasyUITreeNew> newTreeDataList = new ArrayList<EasyUITreeNew>();
        for (EasyUITreeNew jsonTreeData : treeDataList) {
            if (jsonTreeData.getPid() == "0")
                continue;
            // 这是一个子节点
            if (jsonTreeData.getPid().equals(pId)) {
                // 递归获取子节点下的子节点
                List<EasyUITreeNew> childList =  getChildrenNode(jsonTreeData.getId(), treeDataList);
                if(childList.size()>0){
                    jsonTreeData.setChildren(childList);
                    if(jsonTreeData.getChecked() !=null && jsonTreeData.getChecked()){
                        jsonTreeData.setChecked(false); // 重置父节点为未选中，根据子节点状态自动判断
                    }
                }else{
                    jsonTreeData.setState("open");
                }
                
                newTreeDataList.add(jsonTreeData);
            }
        }
        return newTreeDataList;
    }



}
