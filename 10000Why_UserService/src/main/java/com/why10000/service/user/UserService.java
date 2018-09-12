package com.why10000.service.user;

import com.why10000.common.result.R;
import com.why10000.domain.User;
import com.why10000.domain.UserRec;

public interface UserService {
    R save(User user);

    R selectName(String name);
}
