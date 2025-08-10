<#import "../bpmn-template-util.ftl" as util/>
<#import "bpmn-extensions.ftl" as extensions>

<#macro listLanes processLaneSets>

<#if (processLaneSets?size = 0) && !skipEmptySections>
<details>
    <summary><h4>Lanes</h4></summary>
    <#if (processLaneSets?size > 0)>
        <#list processLaneSets as laneset>
            <p>Lane set : <@util.emptyOrNull laneset.name "lane" /> <sup>(${laneset.id})</sup></p>
            <#list laneset.lanes as lane>
                <h5>${lane.name}</h5>
                <sup>(id: ${lane.id})</sup><br/><br/>
                <#if lane.documentation?has_content>
                    <blockquote>${lane.documentation}</blockquote>
                <#else>
                    <@util.emptySection skip=skipEmptySections section="documentation" quote=true markdown=false/>
                </#if>
                <#if lane.extensions?has_content>
                    <p>
                        <@extensions.listExtensions lane.extensions />
                    </p>
                </#if>
            </#list>
        </#list>
    <#else>
        <@util.emptySection skip=skipEmptySections section="lanes" quote=false markdown=false />
    </#if>
</details>
</#if>

</#macro>