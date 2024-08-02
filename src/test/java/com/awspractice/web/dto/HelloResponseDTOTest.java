package com.awspractice.web.dto;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HelloResponseDTOTest {

    @Test
    void 롬복기능테스트() {
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDTO dto = new HelloResponseDTO(name, amount);

        //then
        Assertions.assertThat(dto.getName()).isEqualTo(name);
        Assertions.assertThat(dto.getAmount()).isEqualTo(amount);
        //assertThat은 테스트 검증 라이브러리의 검증 메소드
        //검증하고자하는 대상을 메소드 인자로 받음
        //메소드체이닝이 지원되어 isEqualTo와 같은 메소드 사용 가능

        //isEqualTo는 assertThat에 있는 값과 isEqualTo의 값이 같은지 비교 후 같을때만 성공

    }

}