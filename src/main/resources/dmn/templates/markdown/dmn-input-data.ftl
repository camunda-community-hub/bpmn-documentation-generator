<#import "../dmn-template-util.ftl" as util/>

<#macro listInputData inputdata>

<#--    <details ${openSections}>-->
## Input Data

    <#list inputdata as input>
        * <a href="#${decision.id}"><@util.emptyOrNull decision.name "decision" /></a>
    </#list>

    <#list inputdata as input>
    <#--            <details ${openSections}>-->
### <@util.emptyOrNull input.name "input data"/><sup>(id: ${input.id})</sup>
        <#if input.documentation?has_content>
            > ${input.documentation}
        <#else>
            <@util.emptySection skip=skipEmptySections section="documentation" quote=true markdown=true />
        </#if>
    <#--            </details>-->
    </#list>
<#--    </details> -->
</#macro>