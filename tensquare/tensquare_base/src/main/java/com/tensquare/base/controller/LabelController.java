package com.tensquare.base.controller;

import com.tensquare.base.Service.LabelService;
import com.tensquare.base.pojo.Label;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by 43967 on 2019/2/9.
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true, StatusCode.OK,"查询成功",labelService.findAll());
    }

    @RequestMapping(value = "/{labelId}",method = RequestMethod.GET)
    public Result findById(@PathVariable("labelId") String labelId ){
        return new Result(true, StatusCode.OK,"查询成功",labelService.findById(labelId));
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true, StatusCode.OK,"保存成功");
    }
    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public Result update(@PathVariable("labelId")String lableId, @RequestBody Label label){
        label.setId(lableId);
        labelService.update(label);
        return new Result(true, StatusCode.OK,"修改成功");
    }

    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable("labelId")String lableId){
        labelService.deleteById(lableId);
        return new Result(true, StatusCode.OK,"删除成功");
    }


    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Label label){
        List<Label> labelList=labelService.findSearch(label);
        return  new Result(true,StatusCode.OK,"查询成功",labelList);
    }

    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result pageQuery(@RequestBody Label label,@PathVariable int page,@PathVariable int size){
       Page<Label> pageDate=labelService.pageQuery(label,page,size);
        return  new Result(true,StatusCode.OK,"查询成功",new PageResult<Label>(pageDate.getTotalElements(),pageDate.getContent()));
    }
}
