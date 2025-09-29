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

<details ${openSections}>
    <summary><h4>Gateways</h4></summary>
    <#if (noGateways?size > 0)>
        <#list gatewayTypes as type>
            <#list type as gatewayType, title>
                <#assign gateways = processElements?filter(element -> element.flowType == gatewayType)/>
                <#if (gateways?size > 0)>
                    <h4>${title}</h4>
                    <ul>
                        <#list gateways as gateway>
                            <li><@util.emptyOrNull gateway.name "gateway" /> (${gateway.id})</li>
                        </#list>
                    </ul>
                </#if>
            </#list>
        </#list>
    <#else>
        <@util.emptySection skip=skipEmptySections section="gateways" quote=false markdown=false />
    </#if>
</details>

<#if (noGateways?size > 0)>
    <#list gatewayTypes as type>
        <#list type as gatewayType, title>
            <#assign gateways = processElements?filter(element -> element.flowType == gatewayType)/>
            <#if (gateways?size > 0)>
                <details ${openSections}>
                    <summary><h4>${title}</h4></summary>
                    <#list gateways as gateway>
                        <p>
                            <strong><@util.emptyOrNull gateway.name "gateway" /></strong>
                        <table>
                            <tr>
                                <th>Property</th>
                                <th>Value</th>
                                <th>Version</th>
                            </tr>
                            <tr>
                                <td>ID</td>
                                <td>${gateway.id}</td>
                                <td>n/a</td>
                            </tr>
                            <tr>
                                <td>Template</td>
                                <td><@util.emptyOrNull gateway.template.name "template" /></td>
                                <td><@util.emptyOrNull gateway.template.version "template version" /></td>
                            </tr>
                        </table>
                        <#if gateway.documentation?has_content>
                            <blockquote>${gateway.documentation}</blockquote>
                        <#else>
                            <@util.emptySection skip=skipEmptySections section="gateways" quote=false markdown=false/>
                        </#if>
                        </p>
                        <p>
                            <#if gateway.extensions?has_content>
                                <@extensions.listExtensions gateway.extensions />
                            </#if>
                        </p>
                    </#list>
                </details>
            </#if>
        </#list>
    </#list>
</#if>

</#macro>