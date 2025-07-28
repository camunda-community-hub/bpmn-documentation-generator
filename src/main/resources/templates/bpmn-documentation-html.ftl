<#import "bpmn-template-util.ftl" as util/>
<#import "bpmn-lanes.ftl" as lanes/>
<#import "bpmn-tasks.ftl" as tasks/>
<#import "bpmn-events.ftl" as events/>
<#import "bpmn-gateways.ftl" as gateways/>

<#assign aDateTime = .now/>
<#assign aDate = aDateTime?date/>
<#assign aTime = aDateTime?time/>
<html lang="en">
<head>
    <title>BPMN ${bpmn.fileName}</title>
    <link rel="stylesheet" href="../bpmn/markdown.css" />
    <style>
        .markdown-body {
            box-sizing: border-box;
            min-width: 200px;
            max-width: 980px;
            margin: 0 auto;
            padding: 45px;
        }

        @media (max-width: 767px) {
            .markdown-body {
                padding: 15px;
            }
        }
    </style>
</head>
<body>
<article class="markdown-body">
<h1>BPMN ${bpmn.fileName}</h1>
<p>Generated on ${aDate} at ${aTime}</p>
<p>
    <img alt="BPMN ${bpmn.fileName} image" src="${bpmn.fileName?replace('.bpmn', '.svg', 'i')}"/>
</p>
<table>
    <thead>
    <tr>
        <td>Property</td>
        <td>Value</td>
        <td>Version</td>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>Definitions Id</td>
        <td>${bpmn.id}</td>
        <td>n/a</td>
    </tr>
    <tr>
        <td>Exporter</td>
        <td>${bpmn.exporter}</td>
        <td>${bpmn.exporterVersion}</td>
    </tr>
    <tr>
        <td>Execution Platform</td>
        <td>${bpmn.executionPlatform}</td>
        <td>${bpmn.executionPlatformVersion}</td>
    </tr>
    </tbody>
</table>

<#if bpmn.collaboration?has_content>
    <details>
        <summary><h2>Collaboration</h2></summary>
        <sup>(${bpmn.collaboration.id})</sup><br/><br/>

        <#if bpmn.collaboration.documentation?has_content>
            <blockquote>${bpmn.collaboration.documentation}</blockquote>
        <#else>
            <blockquote>Not documented.</blockquote>
        </#if>

        <#list bpmn.collaboration.extensions>
            <#items as extensionKey, extensionValue>
                <#if extensionKey == "properties">
                    <h4><em>Properties</em></h4>
                    <table>
                        <thead>
                        <tr>
                            <td>Name</td>
                            <td>Value</td>
                        </tr>
                        </thead>
                        <tbody>
                        <#list extensionValue>
                            <#items as itemKey, itemValue>
                                <tr>
                                    <td>${itemKey}</td>
                                    <td>${itemValue}</td>
                                </tr>
                            </#items>
                        </#list>
                        </tbody>
                    </table>
                </#if>
            </#items>
        </#list>

        <#list bpmn.collaboration.extensions>
            <#items as extensionKey, extensionValue>
                <#if extensionKey == "exampleData">
                    <h4><em>Example Data</em></h4>
                    <#list extensionValue>

                        <#items as itemKey, itemValue>
                        <pre>
                            <code class="language-json">
                                ${itemValue}
                            </code>
                        </pre>
                        </#items>
                    </#list>
                </#if>
            </#items>
        </#list>
    </details>

    <#if bpmn.collaboration.participants?has_content>
    <details>
        <summary><h2>Participants</h2></summary>

        <#list bpmn.collaboration.participants>
            <ul>
                <#items as participant>
                    <li><a href="#${participant.id}"><@util.emptyOrNull participant.name "participant" /></a></li>
                </#items>
            </ul>
        </#list>

        <#list bpmn.collaboration.participants>
            <#items as participant>
                <h3 id="${participant.id}"><@util.emptyOrNull participant.name "participant" /></h3>
                <sup>(id: ${participant.id})</sup><br/><br/>
                <#if participant.documentation?has_content>
                    <blockquote>${participant.documentation}</blockquote>
                <#else>
                    <blockquote>Not documented.</blockquote>
                </#if>
                <p>process <a href="#${participant.processRef}">(<strong>${participant.processRef}</strong>)</a></p>

                <#list bpmn.collaboration.extensions>
                    <#items as extensionKey, extensionValue>
                        <#if extensionKey == "properties">
                            <h4><em>Properties</em></h4>
                            <table>
                                <thead>
                                <tr>
                                    <td>Name</td>
                                    <td>Value</td>
                                </tr>
                                </thead>
                                <tbody>
                                <#list extensionValue>
                                    <#items as itemKey, itemValue>
                                        <tr>
                                            <td>${itemKey}</td>
                                            <td>${itemValue}</td>
                                        </tr>
                                    </#items>
                                </#list>
                                </tbody>
                            </table>
                        </#if>
                    </#items>
                </#list>

                <#list participant.extensions>
                    <#items as extensionKey, extensionValue>
                        <#if extensionKey == "exampleData">
                            <h4><em>Example Data</em></h4>

                            <#list extensionValue>
                                <#items as itemKey, itemValue>
                                    <pre>
                                        <code class="language-json">
                                            ${itemValue}
                                        </code>
                                    </pre>
                                </#items>
                            </#list>
                        </#if>
                    </#items>
                </#list>
            </#items>
        <#else>
            <p>None.</p>
        </#list>
    </details>
    </#if> <!-- End of collaboration participants -->
</#if> <!-- End of collaboration -->

<details open>
    <summary><h2>Processes</h2></summary>
    <#if bpmn.processes?has_content>
        <#list bpmn.processes as process>
            <details>
                <summary><h3 id="${process.id}"><@util.emptyOrNull process.name "process"/></h3></summary>
                <sup>(id: ${process.id})</sup><br/><br/>
                <#if process.documentation?has_content>
                    <blockquote>${process.documentation}</blockquote>
                <#else>
                    <blockquote>Not documented.</blockquote>
                </#if>
                <#if process.laneSets?has_content>
                    <@lanes.listLanes processLaneSets=process.laneSets type="html"/>
                </#if>
                <#if process.elements?has_content>
                    <@tasks.listTasks processElements=process.elements type="html"/>
                    <@events.listEvents processElements=process.elements type="html"/>
                    <@gateways.listGateways processElements=process.elements type="html"/>
                </#if>
            </details>
        </#list> <!-- End of Process -->
    <#else>
        <p>No processes present.</p>
    </#if>
</details> <!-- End of processes -->

<details>
    <summary><h2>Globals</h2></summary>
    <h3>Messages</h3>
    <#if bpmn.messages?has_content>
        <#list bpmn.messages>
            <ul>
                <#items as message>
                    <li><@util.emptyOrNull message.name "message" /> <sup>(${message.id})</sup></li>
                </#items>
            </ul>
        </#list>
    <#else>
        <p>No global messages.</p>
    </#if>

    <h3>Errors</h3>
    <#if bpmn.errors?has_content>
        <#list bpmn.errors>
            <ul>
            <#items as error>
                <li><@util.emptyOrNull error.name "error" />} <sup>(${error.id})</sup> - Code ${error.errorCode}</li>
            </#items>
            </ul>
        </#list>
    <#else>
        <p>No global errors.</p>
    </#if>

    <h3>Signals</h3>
    <#if bpmn.signals?has_content>
        <#list bpmn.signals>
            <ul>
            <#items as signal>
                <li><@util.emptyOrNull signal.name "signal" /> <sup>(${signal.id})</sup></li>
            </#items>
            </ul>
        </#list>
    <#else>
        <p>No global signals.</p>
    </#if>

    <h3>Escalations</h3>
    <#if bpmn.escalations?has_content>
        <#list bpmn.escalations>
            <ul>
            <#items as escalation>
                <li><@util.emptyOrNull escalation.name "escalation" /> <sup>(${escalation.id})</sup> - Code ${escalation.escalationCode}</li>
            </#items>
            </ul>
        </#list>
    <#else>
        <p>No global escalations.</p>
    </#if>

</details> <!-- End of Globals -->
</article>
</body>
</html>
