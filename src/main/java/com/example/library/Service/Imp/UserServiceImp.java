package com.example.library.Service.Imp;

import com.example.library.Dto.MapObject.UserDto;
import com.example.library.Entity.User;
import com.example.library.Entity.User_;
import com.example.library.Exeption.BadValues;
import com.example.library.Exeption.NotFoundException;
import com.example.library.Mapping.UserDtoMapper;
import com.example.library.Reposiroties.UserRepository;
import com.example.library.Service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Service
@Slf4j
public class UserServiceImp implements UserService {

    private final UserDtoMapper userDtoMapper;

    private final UserRepository userRepository;

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    public UserServiceImp(UserDtoMapper userDtoMapper, UserRepository userRepository) {
        this.userDtoMapper = userDtoMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.isEmpty()){
            log.debug("User with email{}, not found", username);
            throw new BadValues("Email, not validity");
        }
        User user = userRepository.getUserByEmail(username).orElseThrow(() -> new NotFoundException("User, with this id, not found"));
        log.info("UserDetails created");
        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRole().getAuthorities());
    }

    @Override
    public User getUser(String email) {
        if(email.isEmpty()){
            log.debug("User with email{}, not found", email);
            throw new BadValues("Email, not validity");
        }
        log.info("User selected");
        return userRepository.getUserByEmail(email).orElseThrow(() -> new NotFoundException("User, with this id, not found"));
    }

    @Override
    public boolean findEmail(String email) {
        if(email.isEmpty()){
            log.debug("User with email{}, not found", email);
            throw new BadValues("Email, not validity");
        }
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserDto> cq = cb.createQuery(UserDto.class);
        Root<User> root = cq.from(User.class);

        cq.where(cb.equal(root.get(User_.EMAIL), email));

        cq.multiselect(
                root.get(User_.ID),
                root.get(User_.USERNAME),
                root.get(User_.EMAIL)
        );
        log.info("Email selected");
        return entityManager.createQuery(cq).getResultList().size() != 0;
    }

    @Override
    public UserDto saveUser(User user) {
        if(user == null){
            log.debug("User is null");
            throw new BadValues("User is not true");
        }
        log.info("User saved");
        return userDtoMapper.userToUserDto(userRepository.save(user));
    }

    @Override
    public UserDto getUserProfile(Long id) {
        if(id == null){
            log.debug("User with email{}, not found", id);
            throw new BadValues("Email, not validity");
        }
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserDto> cq = cb.createQuery(UserDto.class);
        Root<User> root = cq.from(User.class);

        cq.where(cb.equal(root.get(User_.ID), id));

        cq.multiselect(
                root.get(User_.ID),
                root.get(User_.USERNAME),
                root.get(User_.EMAIL)
        );
        log.info("Email selected");
        return entityManager.createQuery(cq).getSingleResult();
    }

    @Override
    public void deleteUser(Long id) {
        log.info("Delete user");
        userRepository.deleteById(id);
        log.info("User deleted");
    }

    @Override
    public User getUserById(Long id) {
        if(id == null){
            log.debug("User with email{}, not found", id);
            throw new BadValues("Email, not validity");
        }
        log.info("User selected");
        return userRepository.getUserById(id).orElseThrow(() -> new NotFoundException("User with this id not found"));
    }
}
