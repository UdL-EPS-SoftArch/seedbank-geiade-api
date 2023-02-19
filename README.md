# Seed Bank GEIADE API

Seed Bank project API based on a template for a Spring Boot project including Spring REST, HATEOAS, JPA, etc. Additional details: [HELP.md](HELP.md)

[![Open Issues](https://img.shields.io/github/issues-raw/UdL-EPS-SoftArch/seedbank-geiade-api?logo=github)](https://github.com/orgs/UdL-EPS-SoftArch/projects/17)
[![CI/CD](https://github.com/UdL-EPS-SoftArch/seedbank-geiade-api/actions/workflows/ci-cd.yml/badge.svg)](https://github.com/UdL-EPS-SoftArch/seedbank-geiade-api/actions)
[![CucumberReports: UdL-EPS-SoftArch](https://messages.cucumber.io/api/report-collections/faed8ca5-e474-4a1a-a72a-b8e2a2cd69f0/badge)](https://reports.cucumber.io/report-collections/faed8ca5-e474-4a1a-a72a-b8e2a2cd69f0)
[![Deployment status](https://img.shields.io/uptimerobot/status/m793723785-bfdf35d31009bb587ebc4a92)](https://seedbank-geiade-api.fly.dev)

## Vision

**For** ... **who** want to ...
**the project** ... **is an** ...
**that** allows ...
**Unlike** other ...

## Features per Stakeholder

| USER            | ADMIN             | DONOR           | PROPAGATOR       |
|-----------------|-------------------|-----------------|------------------|
| Register        | Approve User      | List Requests   | List Donations   |
| Login           | Check Donation    | Search Requests | Search Donations |
| Logout          | Alert Seed Needed | Donate          | Take             |
| List Takes      |                   | Match           |                  |
| Search Takes    |                   |                 |                  |
| View Profile    |                   |                 |                  |
| Post Blog Entry |                   |                 |                  |
| Edit Profile    |                   |                 |                  |

### Features' Details

* **List** / **Search**: when listing or searching, show first older, nearer seeds.
* **Take** is allowed if the ration between amount taken and that donated is not too negative. Ratio definition to be discussed...
* **Match**: **Donations** might be matched to existing **Requests** based on ratio, date and location, though **Donors** can override the match and pick their preferred **Request**.
* **View Profile**: it is possible to view public **Profiles**, though **Admins** can view all and users always their own.

## Entities Model

![EntityModelsDiagram](https://www.plantuml.com/plantuml/svg/5Sqnhi8m303G_P_YSu34ErCPke64L740DpQDHU94YIsk3qnlVCiq7EQr_9r4Kt5Y7-K_c_Lv1XZuZeTOzjqdZzJKM2scLk51rx3Ujx2rfrr6oZ2PQKTzXOC5YGDsWOgYiAg92SzR8own_wg5bRv-0000?v0)

