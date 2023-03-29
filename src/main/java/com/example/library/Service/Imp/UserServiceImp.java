package com.example.library.Service.Imp;

import com.example.library.Dto.Security.PersonDetails;
import com.example.library.Dto.Security.RegistrationDto;
import com.example.library.Dto.Security.UserPrincipalData;
import com.example.library.Dto.User.UserDto;
import com.example.library.Dto.User.UserUpdateDto;
import com.example.library.Entity.Role;
import com.example.library.Entity.User;
import com.example.library.Entity.User_;
import com.example.library.Exeption.BadValues;
import com.example.library.Exeption.NotFoundException;
import com.example.library.Mapping.UserDtoMapper;
import com.example.library.Reposiroties.UserRepository;
import com.example.library.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImp implements UserService {

    private final UserDtoMapper userDtoMapper;

    private final UserRepository userRepository;

    private final UserPrincipalData userPrincipalData;

    @PersistenceContext
    public EntityManager entityManager;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if (username.isEmpty()) {
            throw new BadValues("Email, not validity");
        }
        User user = userRepository.getUserByEmail(username).orElseThrow(() -> new NotFoundException("User not found"));
        return new PersonDetails(user.getId(), user.getEmail(), user.getPassword(), user.getRole());
    }

    @Override
    public boolean findEmail(String email) {
        log.info("Find email");
        if (email.isEmpty()) {
            throw new BadValues("Email, not validity");
        }
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserDto> cq = cb.createQuery(UserDto.class);
        Root<User> root = cq.from(User.class);

        cq.where(cb.equal(root.get(User_.EMAIL), email));

        cq.multiselect(
                root.get(User_.ID),
                root.get(User_.userName),
                root.get(User_.EMAIL)
        );
        return entityManager.createQuery(cq).getResultList().size() != 0;
    }

    @Override
    public void saveUser(RegistrationDto registration) {
        log.info("Save user");
        if (registration == null) {
            throw new BadValues("Saved user is invalid");
        }
        userRepository.save(userDtoMapper.registrationToUser(registration));
    }

    @Override
    @Transactional
    public UserDto updateUser(UserUpdateDto userUpdateDto) {
        log.info("Update user with id {}", userUpdateDto.getId());
        User user = userRepository.findById(userUpdateDto.getId())
                .orElseThrow(() -> new NotFoundException("User not found"));
        if(userUpdateDto.getUserName() != null){
            user.setUserName(userUpdateDto.getUserName());
        }

        if(userUpdateDto.getEmail() != null){
            user.setEmail(userUpdateDto.getEmail());
        }

        if(userUpdateDto.getPassword() != null){
            user.setPassword(userUpdateDto.getPassword());
        }

        if(userPrincipalData.getRole() == Role.ADMIN && userUpdateDto.getRole() != null){
            user.setRole(userUpdateDto.getRole());
        }

        if(userPrincipalData.getRole() == Role.ADMIN && userUpdateDto.getSummary() != null){
            user.setSummary(user.getSummary() + userUpdateDto.getSummary());
        }
        return userDtoMapper.userToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto getUserByEmail(String email) {
        log.info("Get user by email {}", email);
        return userDtoMapper.userToUserDto(userRepository.getUserByEmail(email)
                .orElseThrow(() -> new NotFoundException("User not found")));
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Delete user with id {}", id);
        userRepository.deleteById(id);
    }

    @Override
    public UserDto getUserById(Long id) {
        log.info("Delete user with id {}", id);
        if (id == null) {
            throw new BadValues("User not found");
        }
        return userDtoMapper.userToUserDto(userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found")));
    }
}
