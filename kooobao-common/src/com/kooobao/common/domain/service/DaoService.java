package com.kooobao.common.domain.service;

import com.kooobao.common.domain.dao.Dao;

/**
 * DaoService is used to expose a {@link Dao} as a remote service
 * 
 * @author harper
 * @since kooobao-common 1.0
 * @version 1.0
 * 
 * 
 * @param <T>
 */
public interface DaoService<T> extends Dao<T> {

}
