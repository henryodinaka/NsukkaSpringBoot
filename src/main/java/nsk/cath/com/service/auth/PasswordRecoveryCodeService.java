package nsk.cath.com.service.auth;

import nsk.cath.com.model.auth.PasswordRecoveryCode;
import nsk.cath.com.repo.auth.PasswordRecoveryCodeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PasswordRecoveryCodeService {
    private PasswordRecoveryCodeRepo passwordRecoveryCodeRepo;

    @Autowired
    public void setPasswordRecoveryCodeRepo(PasswordRecoveryCodeRepo passwordRecoveryCodeRepo) {
        this.passwordRecoveryCodeRepo = passwordRecoveryCodeRepo;
    }

    public PasswordRecoveryCode findRecoveryCode(String recoveryCode)
    {
        return passwordRecoveryCodeRepo.findRecoveryCode(recoveryCode);
    }
}
