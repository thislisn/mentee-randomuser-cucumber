package framework.properies;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Properties {
    BROWSER("browser");
    private String fieldName;
}
