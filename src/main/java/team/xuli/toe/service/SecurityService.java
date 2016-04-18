package team.xuli.toe.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import team.xuli.toe.dao.IUserDao;
import team.xuli.toe.domain.User;
import team.xuli.toe.util.AppConst;
import team.xuli.toe.util.Messages;

/**
 * @author: 徐清锋
 * 创建时间：2016/4/3
 * 创建原因：
 */
@Service
public class SecurityService implements ISecurityService {
    @Autowired
    private IUserDao userDao;

    public boolean remitToAdmin(int payerId, int payment){
        this.remit(payerId, AppConst.ADMIN_ID, payment);
        return true;
    }

    public boolean remitFromAdmin(int payeeId, int payment){
        this.remit(AppConst.ADMIN_ID, payeeId, payment);
        return true;
    }

    @Transactional
    public boolean remit(int payerId, int payeeId, int payment){
        User payer = userDao.getById(payerId);
        User payee = userDao.getById(payeeId);

        payee.setMoney(payee.getMoney() + payment);
        payer.setMoney(payer.getMoney() - payment);
        if(payer.getMoney() < 0){
            throw new RuntimeException(Messages.INSUFFICIENT_FUNDS);
        }
        userDao.update(payee);
        userDao.update(payer);
        return true;
    }
    @Transactional
    public boolean creditIncrease(int userId, int value){
        User user = userDao.getById(userId);
        user.setCredit(user.getCredit() + value);
        userDao.update(user);
        return true;
    }
    public User currentUser(){
        User currentUser;
        try {
            Object tmp = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            currentUser = (User) tmp;
        }catch(Exception e) {
            throw new RuntimeException(Messages.NO_USER_LOGIN);
        }
        return currentUser;
    }
}
