<#import "../bpmn-template-util.ftl" as util/>

<#macro listLanes processLaneSets>

#### Lanes

<#list processLaneSets as laneset>
Lane set : <@util.emptyOrNull laneset.name "lane" /> <sup>(${laneset.id})</sup>
    <#list laneset.lanes as lane>
##### ${lane.name}
<sup>(id: ${lane.id})</sup>
        <#if lane.documentation?has_content>
> ${lane.documentation}
        <#else>
> Not documented.
        </#if>
    </#list> <!-- End of Lanes -->
</#list> <!-- End of LaneSets -->

</#macro>