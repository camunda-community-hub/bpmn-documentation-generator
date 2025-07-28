<#import "bpmn-template-util.ftl" as util/>

<#macro listLanes processLaneSets type>

<#if type == "markdown">

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

<#else>
    <details>
        <summary><h4>Lanes</h4></summary>
        <#list processLaneSets as laneset>
            <p>Lane set : <@util.emptyOrNull laneset.name "lane" /> <sup>(${laneset.id})</sup></p>
            <#list laneset.lanes as lane>
                <h5>${lane.name}</h5>
                <sup>(id: ${lane.id})</sup><br/><br/>
                <#if lane.documentation?has_content>
                    <blockquote>${lane.documentation}</blockquote>
                <#else>
                    <blockquote>Not documented.</blockquote>
                </#if>
            </#list> <!-- End of Lanes -->
        </#list> <!-- End of LaneSets -->
    </details>
</#if>

</#macro>