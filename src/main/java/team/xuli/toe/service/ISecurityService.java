package team.xuli.toe.service;

import team.xuli.toe.domain.User;

/**
 * @author: 徐清锋
 * 创建时间：2016/4/3
 * 创建原因：
 */
public interface ISecurityService {
    boolean remit(int payerId, int payeeId, int payment);
    boolean remitToAdmin(int payerId, int payment);
    boolean remitFromAdmin(int payeeId, int payment);
    boolean creditIncrease(int userId, int value);
    User currentUser();
}
