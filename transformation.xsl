<?xml version = "1.0" encoding = "UTF-8"?>
<xsl:stylesheet version = "1.0"
                xmlns:xsl = "http://www.w3.org/1999/XSL/Transform">

    <xsl:template match="/profile">
        <html>
            <head>
                <title>European E-competence</title>
                <meta http-equiv="Content-Style-Type" content="text/css" />
                <link rel="stylesheet" href="styling.css" type="text/css" media="screen" />
            </head>
            <body>
                <h1>
                    <xsl:apply-templates select="title"/>
                </h1>
                <h2>Summary</h2>
                <div>
                    <xsl:value-of select="summary"/>
                </div>
                <h2>Mission</h2>
                <xsl:apply-templates select="mission"/>
                <h2>Deliverables</h2>
                <xsl:apply-templates select="deliverables"/>
                <h2>Tasks</h2>
                <xsl:apply-templates select="tasks"/>
                <xsl:apply-templates select="competences"/>
                <h2>KPI</h2>
                <div>
                    <xsl:value-of select="kpi"/>
                </div>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="title">
        <xsl:value-of select="."/>
    </xsl:template>

    <xsl:template match="mission">
        <div>
            <xsl:value-of select="."/>
        </div>
    </xsl:template>

    <xsl:template match="/deliverables">
        <xsl:apply-templates select="accountable"/>
        <xsl:apply-templates select="responsible"/>
        <xsl:apply-templates select="contributor"/>
    </xsl:template>

    <xsl:template match='accountable'>
        <h3>Accountable</h3>
        <xsl:apply-templates select="deliverable"/>
    </xsl:template>

    <xsl:template match='responsible'>
        <h3>Responsible</h3>
        <xsl:apply-templates select="deliverable"/>
    </xsl:template>

    <xsl:template match='contributor'>
        <h3>Contributor</h3>
        <xsl:apply-templates select="deliverable"/>
    </xsl:template>

    <xsl:template match="deliverable">
        <li>
            <xsl:value-of select="."/>
        </li>
    </xsl:template>

    <xsl:template match='tasks'>
        <xsl:apply-templates select="task"/>
    </xsl:template>

    <xsl:template match="task">
        <div>
            <xsl:value-of select="."/>
        </div>
    </xsl:template>

    <xsl:template match="competences">
        <h2>Competences</h2>
        <xsl:apply-templates select="competence"/>
    </xsl:template>

    <xsl:template match="competence">
        <ol>
            <xsl:value-of select="."/>
            <xsl:text>Level: </xsl:text>
            <xsl:value-of select="./@level"/>
        </ol>
    </xsl:template>
</xsl:stylesheet>