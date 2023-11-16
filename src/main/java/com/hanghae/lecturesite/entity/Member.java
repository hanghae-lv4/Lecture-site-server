package com.hanghae.lecturesite.entity;



import com.hanghae.lecturesite.dto.SignupRequestDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "member")
@NoArgsConstructor

public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private char gender;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MemberRoleEnum role;

    public Member(SignupRequestDto requestDto) {
        this.email = requestDto.getEmail();
        this.gender = requestDto.getGender();
        this.phone = requestDto.getPhone();
        this.address = requestDto.getAddress();
        this.role = MemberRoleEnum.valueOf(requestDto.getRole());
    }
}
