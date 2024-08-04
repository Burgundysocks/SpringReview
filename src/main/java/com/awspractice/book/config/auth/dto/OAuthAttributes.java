package com.awspractice.book.config.auth.dto;

import com.awspractice.book.domain.dto.Role;
import com.awspractice.book.domain.dto.UserDTO;
import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
public class OAuthAttributes {

    private Map<String,Object> attributes;
    private String nameAttributeKey, name, email;

    @Builder
    public OAuthAttributes(Map<String,Object> attributes,
                           String nameAttributeKey,
                           String name,String email){
        this.attributes=attributes;
        this.nameAttributeKey=nameAttributeKey;
        this.name=name;
        this.email=email;
    }
    public static OAuthAttributes of(String registrationId,
                                     String userNameAttributeName,
                                     Map<String, Object> attributes) {
        return ofGoogle(userNameAttributeName, attributes);
    }
    //of는 OAuth2User에서 반환하는 사용자 정보는 MAP이기 때문에 값 하나하나를 변환해야만 한다.


    public static OAuthAttributes ofGoogle(String userNameAttributeName,
                                           Map<String, Object> attributes) {
        return OAuthAttributes.builder()
                .name((String) attributes.get("name"))
                .email((String) attributes.get("email"))
                .attributes(attributes)
                .nameAttributeKey(userNameAttributeName)
                .build();
    }

    public UserDTO toEntity() {
        return UserDTO.builder()
                .name(name)
                .email(email)
                .role(Role.GUEST)
                .build();
    }
    //USER ENTITY 생성
    //OAuthAttributes에서 엔티티를 생성하는 시점은 처음가입할 때
    //이때 기본적으로 권한을 GUEST를 줌
    //생성이 끝나면 SessionUser 클래스 추가


}
