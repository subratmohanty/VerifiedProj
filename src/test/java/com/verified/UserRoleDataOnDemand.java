package com.verified;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;

@Configurable
@Component
public class UserRoleDataOnDemand {

	private Random rnd = new SecureRandom();

	private List<UserRole> data;

	public UserRole getNewTransientUserRole(int index) {
        UserRole obj = new UserRole();
        setRoleName(obj, index);
        return obj;
    }

	public void setRoleName(UserRole obj, int index) {
        String RoleName = "RoleName_" + index;
        obj.setRoleName(RoleName);
    }

	public UserRole getSpecificUserRole(int index) {
        init();
        if (index < 0) {
            index = 0;
        }
        if (index > (data.size() - 1)) {
            index = data.size() - 1;
        }
        UserRole obj = data.get(index);
        Long id = obj.getId();
        return UserRole.findUserRole(id);
    }

	public UserRole getRandomUserRole() {
        init();
        UserRole obj = data.get(rnd.nextInt(data.size()));
        Long id = obj.getId();
        return UserRole.findUserRole(id);
    }

	public boolean modifyUserRole(UserRole obj) {
        return false;
    }

	public void init() {
        int from = 0;
        int to = 10;
        data = UserRole.findUserRoleEntries(from, to);
        if (data == null) {
            throw new IllegalStateException("Find entries implementation for 'UserRole' illegally returned null");
        }
        if (!data.isEmpty()) {
            return;
        }
        
        data = new ArrayList<UserRole>();
        for (int i = 0; i < 10; i++) {
            UserRole obj = getNewTransientUserRole(i);
            try {
                obj.persist();
            } catch (final ConstraintViolationException e) {
                final StringBuilder msg = new StringBuilder();
                for (Iterator<ConstraintViolation<?>> iter = e.getConstraintViolations().iterator(); iter.hasNext();) {
                    final ConstraintViolation<?> cv = iter.next();
                    msg.append("[").append(cv.getRootBean().getClass().getName()).append(".").append(cv.getPropertyPath()).append(": ").append(cv.getMessage()).append(" (invalid value = ").append(cv.getInvalidValue()).append(")").append("]");
                }
                throw new IllegalStateException(msg.toString(), e);
            }
            obj.flush();
            data.add(obj);
        }
    }
}
