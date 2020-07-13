package framework.dto;

import lombok.Data;

import java.util.List;

@Data
public class RandomUserNameDto {
    private List<Results> results;
    private Info info;
}
