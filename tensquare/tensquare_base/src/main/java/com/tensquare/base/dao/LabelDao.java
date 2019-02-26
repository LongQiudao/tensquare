package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by 43967 on 2019/2/9.
 */
public interface LabelDao  extends JpaRepository<Label,String>,JpaSpecificationExecutor<Label> {

}
