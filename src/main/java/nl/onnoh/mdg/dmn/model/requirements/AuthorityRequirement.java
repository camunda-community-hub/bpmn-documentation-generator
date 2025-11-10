package nl.onnoh.mdg.dmn.model.requirements;

import lombok.Data;

@Data
public class AuthorityRequirement {
    String id;
    String description;
    String requiredDecisionHref;
    String requiredInputHref;
    String requiredAuthorityHref;
}
