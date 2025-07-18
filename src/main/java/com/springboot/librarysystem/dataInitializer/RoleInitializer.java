package com.springboot.librarysystem.dataInitializer;
import com.springboot.librarysystem.constants.RoleNames;
import com.springboot.librarysystem.entity.auth.Role;
import com.springboot.librarysystem.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RoleInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;



    @Override
    public void run(String... args) {
        for (RoleNames roleNames : RoleNames.values()) {
            if (!roleRepository.existsRoleByRole(roleNames)) {
                roleRepository.save(new Role(roleNames));
            }
        }
    }
}
