package br.com.leonardoferreira.jirareport.service.impl;

import br.com.leonardoferreira.jirareport.domain.Project;
import br.com.leonardoferreira.jirareport.domain.vo.AccountVO;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author lferreira
 * @since 11/14/17 3:53 PM
 */
public abstract class AbstractService {

    protected AccountVO currentUser() {
        return (AccountVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    protected String currentToken() {
        return currentUser().getToken();
    }

}