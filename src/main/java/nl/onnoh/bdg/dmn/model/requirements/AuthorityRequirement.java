package nl.onnoh.bdg.dmn.model.requirements;

import lombok.Data;

@Data
public class AuthorityRequirement {
    String id;
    String documentation;
    RequiredInput requiredInput;
    RequiredAuthority requiredAuthority;
}
