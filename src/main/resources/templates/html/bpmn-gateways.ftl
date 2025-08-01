<#import "../bpmn-template-util.ftl" as util/>
<#macro listGateways processElements>

<#assign gatewayTypes = [
{ "complexGateway": "Complex Gateways" },
{ "inclusiveGateway": "Inclusive Gateways" },
{ "exclusiveGateway": "Send Gateways" },
{ "eventBasedGateway": "Event Based Gateways" },
{ "parallelGateway": "Parallel Gateways" }
]/>

<#assign noGateways = processElements?filter(element -> element.flowType?ends_with("Gateway"))/>

<details>
    <summary><h4>Gateways</h4></summary>
    <#if (noGateways?size > 0)>
        <#list gatewayTypes as type>
            <#list type as gatewayType, title>
                <#assign gateways = processElements?filter(element -> element.flowType == gatewayType)/>
                <#if (gateways?size > 0)>
                    <h5>${title}</h5>
                    <ul>
                        <#list gateways as gateway>
                            <li><@util.emptyOrNull gateway.name "gateway" /> (${gateway.id})</li>
                        </#list>
                    </ul>
                </#if>
            </#list>
        </#list>
    <#else>
        <p>No gateways.</p>
    </#if>
</details>

</#macro>