package com.aledma.hackathonBEfinal.domain;

import com.aledma.hackathonBEfinal.dto.UserDto;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Builder
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @Email
    private String email;

    @Column
    private String password;

    // CascadeType.ALL 설정 해야하나?
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<WebScraping> webScrapings = new ArrayList<>();

    //추가
//    @ManyToMany
//    @JoinTable(
//            name = "user_favorite_webscrapings",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "web_scraping_id")
//    )
//    private List<WebScraping> favoriteWebScrapings = new ArrayList<>();

    public static User of(UserDto userDto){
        return User.builder()
                .email(userDto.getEmail())
                .password(userDto.getPassword())
                .build();
    }
}
