package com.mtsmda.spring.helper;

import com.mtsmda.spring.helper.response.CommonResponse;

import java.util.List;

/**
 * Created by MTSMDA on 09.11.2016.
 */
public interface StandardRepository<T> {

    CommonResponse<Boolean> insert(T tObject);
    CommonResponse<Boolean> update(T tObject);
    CommonResponse<Boolean> delete(T tObject);
    CommonResponse<Boolean> deleteByUniqueFieldsOrPrimaryKey(T tObject);
    CommonResponse<T> getById(Integer id);
    CommonResponse<List<T>> getAll();

}