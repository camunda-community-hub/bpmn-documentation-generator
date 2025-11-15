<#import "../dmn-template-util.ftl" as util/>

<#macro listInputData inputdata>

<#--    <details ${openSections}>-->
## Input Data

    <#list inputdata as input>
<#assign inputName><#compress><@util.emptyOrNull input.name "input data" /></#compress></#assign>
* [${inputName}](#${input.id})
    </#list>

    <#list inputdata as input>
<#assign inputName><#compress><@util.emptyOrNull input.name "input data" /></#compress></#assign>
<a id="${input.id}"></a>
### ${inputName?trim}

<@util.showDocumentation input.description />
    </#list>

</#macro>