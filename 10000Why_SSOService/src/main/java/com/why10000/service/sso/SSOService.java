package com.why10000.service.sso;

import com.why10000.common.result.R;
import org.springframework.stereotype.Component;

@Component("ssoService")
public interface SSOService {

    R login(String name, String pass);

    R loginOut(String token);

    R loginCheck(String token);
}
