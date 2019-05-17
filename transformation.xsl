<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0"
                xmlns="http://www.w3.org/1999/xhtml"
                xmlns:r="http://recipes.org">

    <xsl:output method="xml"
                doctype-public="-//W3C//DTD XHTML 1.0 Strict//EN"
                doctype-system="http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd"
                encoding="UTF-8"
                indent="yes"
    />

    <xsl:template match="/r:profile">
        <html>
            <head>
                <title>European E-competence</title>
                <meta http-equiv="Content-Style-Type" content="text/css" />
                <link rel="stylesheet" href="styling.css" type="text/css" media="screen" />
            </head>
            <body>
                <h1>
                    <xsl:apply-templates select="r:title"/>
                </h1>
                <h2>Summary</h2>
                <div>
                    <xsl:value-of select="r:summary"/>
                </div>
                <h2>Mission</h2>
                <xsl:apply-templates select="r:mission"/>
                <h2>Deliverables</h2>
                <xsl:apply-templates select="r:deliverables"/>
                <h2>Tasks</h2>
                <xsl:apply-templates select="r:tasks"/>
                <xsl:apply-templates select="r:competences"/>
                <h2>KPI</h2>
                <div>
                        <xsl:value-of select="r:kpi"/>
                </div>
            </body>
        </html>
    </xsl:template>

    <xsl:template match="r:title">
        <xsl:value-of select="."/>
    </xsl:template>

    <xsl:template match="r:mission">
        <div>
            <xsl:value-of select="."/>
        </div>
    </xsl:template>

    <xsl:template match="/r:deliverables">
        <xsl:apply-templates select="r:accountable"/>
        <xsl:apply-templates select="r:responsible"/>
        <xsl:apply-templates select="r:contributor"/>
    </xsl:template>

    <xsl:template match='r:accountable'>
        <h3>Accountable</h3>
        <xsl:apply-templates select="r:deliverable"/>
    </xsl:template>

    <xsl:template match='r:responsible'>
        <h3>Responsible</h3>
        <xsl:apply-templates select="r:deliverable"/>
    </xsl:template>

    <xsl:template match='r:contributor'>
        <h3>Contributor</h3>
        <xsl:apply-templates select="r:deliverable"/>
    </xsl:template>

    <xsl:template match="r:deliverable">
        <li>
            <xsl:value-of select="."/>
        </li>
    </xsl:template>

    <xsl:template match='r:tasks'>
        <xsl:apply-templates select="r:task"/>
    </xsl:template>

    <xsl:template match="r:task">
        <div>
            <xsl:value-of select="."/>
        </div>
    </xsl:template>

    <xsl:template match="r:competences">
        <h2>Competences</h2>
        <xsl:apply-templates select="r:competence"/>
    </xsl:template>

    <xsl:template match="r:competence">
        <ol>
            <xsl:value-of select="."/>
            <xsl:text>Level: </xsl:text>
            <xsl:value-of select="./@level"/>
        </ol>
    </xsl:template>
</xsl:stylesheet>