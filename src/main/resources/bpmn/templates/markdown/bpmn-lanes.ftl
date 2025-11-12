<#import "../bpmn-template-util.ftl" as util/>

<#macro listLanes processLaneSets>

#### Lanes

<#list processLaneSets as laneset>
Lane set : <@util.emptyOrNull laneset.name "lane set" />

    <#list laneset.lanes as lane>
##### <@util.emptyOrNull lane.name "lane" />
        <#if lane.documentation?has_content>
> ${lane.documentation}
        <#else>
> Not documented.
        </#if>
    </#list>

</#list>

</#macro>