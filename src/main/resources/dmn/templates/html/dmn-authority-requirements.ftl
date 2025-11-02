<#import "../dmn-template-util.ftl" as util />

<#macro listAuthorityRequirements authorityRequirements>

    <#if authorityRequirements?has_content>
        <h3>Authority Requirements</h3>
        <#list authorityRequirements as authorityRequirement>
            <p>
                ${authorityRequirement.id}
                <@util.showDocumentation authorityRequirement.description />
                <#if authorityRequirement.requiredDecisionHref?has_content>
                    Decision ${authorityRequirement.requiredDecisionHref}
                </#if>
                <#if authorityRequirement.requiredInputHref?has_content>
                    Input ${authorityRequirement.requiredInputHref}
                </#if>
                <#if authorityRequirement.requiredAuthorityHref?has_content>
                    Authority ${authorityRequirement.requiredAuthorityHref}
                </#if>
            </p>
        </#list>
    </#if>

</#macro>