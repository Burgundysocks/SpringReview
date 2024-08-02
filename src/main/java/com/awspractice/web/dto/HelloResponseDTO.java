package com.awspractice.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
//선언된 모든 필드의 get메소드 생성
@RequiredArgsConstructor
//선언된 final필드에 기본생성자 생성
//final이 붙지 않은 경우 생성하지 않음
public class HelloResponseDTO {
    private final String name;
    private final int amount;
}
