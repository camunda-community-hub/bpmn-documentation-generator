<#import "../dmn-template-util.ftl" as util/>

<#macro listDecisions decisions>

<#--    <details ${openSections}>-->
## Decisions

<#list decisions as decision>
* <a href="#${decision.id}"><@util.emptyOrNull decision.name "decision" /></a>
</#list>

<#list decisions as decision>
<#--            <details ${openSections}>-->
### <@util.emptyOrNull decision.name "decision"/><sup>(id: ${decision.id})</sup>
<#if decision.documentation?has_content>
> ${decision.documentation}
<#else>
    <@util.emptySection skip=skipEmptySections section="documentation" quote=true markdown=true />
</#if>
<#--            </details>-->
</#list>
<#--    </details> -->
</#macro>