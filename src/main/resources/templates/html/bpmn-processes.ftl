<#import "../bpmn-template-util.ftl" as util/>
<#import "bpmn-lanes.ftl" as lanes/>
<#import "bpmn-tasks.ftl" as tasks/>
<#import "bpmn-events.ftl" as events/>
<#import "bpmn-gateways.ftl" as gateways/>

<#macro listProcesses processes>

<details>
    <summary><h2>Processes</h2></summary>

    <ul>
        <#list bpmn.processes as process>
            <li><a href="#${process.id}"><@util.emptyOrNull process.name "process" /></a></li>
        </#list>
    </ul>

    <#list bpmn.processes as process>
        <details>
            <summary><h3 id="${process.id}"><@util.emptyOrNull process.name "process"/></h3></summary>
            <sup>(id: ${process.id})</sup><br/><br/>
            <#if process.documentation?has_content>
                <blockquote>${process.documentation}</blockquote>
            <#else>
                <@util.emptySection skip=skipEmptySections section="documentation" quote=true markdown=false />
            </#if>
            <#if process.laneSets?has_content>
                <@lanes.listLanes processLaneSets=process.laneSets />
            </#if>
            <#if process.elements?has_content>
                <@tasks.listTasks processElements=process.elements />
                <@events.listEvents processElements=process.elements />
                <@gateways.listGateways processElements=process.elements />
            </#if>
        </details>
    </#list> <!-- End of Process -->

</details> <!-- End of processes -->


</#macro>