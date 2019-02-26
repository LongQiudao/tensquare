package com.tensquare.base.Service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 43967 on 2019/2/9.
 */

@Service
@Transactional
public class LabelService {

    @Autowired
    private LabelDao labelDao;

    @Autowired
    private IdWorker idWorker;

    public List<Label> findAll(){
        return labelDao.findAll();
    }

    public Label findById(String id){
       return labelDao.findById(id).get();
    }

    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }
    public void update(Label label){
        labelDao.save(label);
    }

    public void deleteById(String id){
        labelDao.deleteById(id);
    }

    public List<Label> findSearch(Label label) {
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //new一个集合来存放所有的条件
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if(label.getLabelname()!=null && !"".equals(label.getLabelname())){
                    Predicate  predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    predicateList.add(predicate);
                }
                if(label.getState()!=null && !"".equals(label.getState())){
                    Predicate  predicate = criteriaBuilder.equal(root.get("state").as(String.class),  label.getState());
                    predicateList.add(predicate);
                }
                //new一个数组作为最终返回的条件
                Predicate[] parr= new Predicate[predicateList.size()];
                //把list集合转为数组
                predicateList.toArray(parr);
                return criteriaBuilder.and(parr);
            }
        });
    }

    public Page<Label> pageQuery(Label label, int page, int size) {
        //封装分页对象
        Pageable pageable = PageRequest.of(page-1,size);
        return labelDao.findAll(new Specification<Label>() {
            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //new一个集合来存放所有的条件
                List<Predicate> predicateList = new ArrayList<Predicate>();
                if(label.getLabelname()!=null && !"".equals(label.getLabelname())){
                    Predicate  predicate = criteriaBuilder.like(root.get("labelname").as(String.class), "%" + label.getLabelname() + "%");
                    predicateList.add(predicate);
                }
                if(label.getState()!=null && !"".equals(label.getState())){
                    Predicate  predicate = criteriaBuilder.equal(root.get("state").as(String.class),  label.getState());
                    predicateList.add(predicate);
                }
                //new一个数组作为最终返回的条件
                Predicate[] parr= new Predicate[predicateList.size()];
                //把list集合转为数组
                predicateList.toArray(parr);
                return criteriaBuilder.and(parr);
            }
        },pageable);
    }
}
