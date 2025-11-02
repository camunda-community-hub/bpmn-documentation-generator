package nl.onnoh.bdg.dmn.model.requirements;

import lombok.Data;

@Data
public class AuthorityRequirement {
    String id;
    String description;
    String requiredDecisionHref;
    String requiredInputHref;
    String requiredAuthorityHref;
}
