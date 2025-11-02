<#import "../dmn-template-util.ftl" as util/>

<#macro listInputData inputdata>

<#--    <details ${openSections}>-->
## Input Data

    <#list inputdata as input>
* <a href="#${input.id}"><@util.emptyOrNull input.name "input data" /></a>
    </#list>

    <#list inputdata as input>
    <#--            <details ${openSections}>-->
### <@util.emptyOrNull input.name "input data"/><sup>(id: ${input.id})</sup>
<@util.showDocumentation input.description />
    <#--            </details>-->
    </#list>
<#--    </details> -->
</#macro>