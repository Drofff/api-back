package ua.ozzy.apiback.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.ozzy.apiback.configuration.properties.DefaultAdminProperties;
import ua.ozzy.apiback.model.Admin;
import ua.ozzy.apiback.repository.AdminRepository;
import ua.ozzy.apiback.util.DbUtil;

import static ua.ozzy.apiback.util.ValidationUtil.validateNotNull;

@Service
public class AdminServiceImpl implements AdminService {

    private static final Logger LOG = LoggerFactory.getLogger(AdminServiceImpl.class);

    private final AdminRepository adminRepository;

    private final PasswordEncoder passwordEncoder;

    private final DefaultAdminProperties defaultAdminProperties;

    public AdminServiceImpl(AdminRepository adminRepository, PasswordEncoder passwordEncoder,
                            DefaultAdminProperties defaultAdminProperties) {
        this.adminRepository = adminRepository;
        this.passwordEncoder = passwordEncoder;
        this.defaultAdminProperties = defaultAdminProperties;
    }

    @Override
    public void createDefaultAdmin() {
        if (missingDefaultAdmin()) {
            LOG.info("Creating default administrator account");
            Admin defaultAdmin = adminOfDefaultProperties();
            defaultAdmin.setId(DbUtil.generateId());
            adminRepository.save(defaultAdmin);
        }
    }

    private boolean missingDefaultAdmin() {
        return !doesDefaultAdminExist();
    }

    private boolean doesDefaultAdminExist() {
        String username = defaultAdminProperties.getUsername();
        return adminRepository.findByUsername(username).isPresent();
    }

    private Admin adminOfDefaultProperties() {
        String username = defaultAdminProperties.getUsername();
        validateNotNull(username, "Missing default admin username configuration");
        String password = defaultAdminProperties.getPassword();
        validateNotNull(password, "Missing default admin password configuration");
        return asAdmin(username, password);
    }

    private Admin asAdmin(String username, String password) {
        String encodedPassword = passwordEncoder.encode(password);
        return new Admin.Builder()
                .username(username)
                .password(encodedPassword)
                .build();
    }

}
