package com.backpacker.homework.member.domian.entity;

import com.backpacker.homework.code.Gender;
import com.backpacker.homework.common.BaseEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Entity
@ToString
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberEntity extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long memberId;

    @Column(name = "name")
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "password")
    private String password;

    @Column(name = "tel_number")
    private String telNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = new ArrayList<>();
        roles.add("ROLE_USER");
        return roles.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Builder
    public MemberEntity(String name,
                        String nickname,
                        String password,
                        String telNumber,
                        String email,
                        Gender gender) {
        this.name = name;
        this.nickname = nickname;
        this.password = password;
        this.telNumber = telNumber;
        this.email = email;
        this.gender = gender;
    }

    public static MemberEntity createBy(String name,
                                        String nickname,
                                        String password,
                                        String telNumber,
                                        String email,
                                        Gender gender) {
        return MemberEntity.builder()
                .name(name)
                .nickname(nickname)
                .password(password)
                .telNumber(telNumber)
                .email(email)
                .gender(gender)
                .build();
    }

}
