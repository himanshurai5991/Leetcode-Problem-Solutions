# LinkedIn Profile Review

Review of the LinkedIn profile export (`Profile.pdf`, 3 pages), 2026-08-06.

Context: Software Engineer III at PayPay (Tokyo) since Jan 2023 — core payment / payout services.
Prior: BlackBuck (Oct 2021 – Nov 2022), Walmart Global Tech (Nov 2020 – Sep 2021),
Infinite Computer Solutions (Aug 2018 – Oct 2020). BE CS, IERT Allahabad (2014 – 2018).

---

## Verified as correct — do not change

- **Timeline is contiguous and accurate.** Walmart ends Sep 2021, BlackBuck starts Oct 2021,
  and Oct 2021 → Nov 2022 is genuinely 1 yr 2 mo. No gaps, no overlaps, no arithmetic errors.
  (An earlier read of mine flagged an inconsistency here; that was wrong.)

## What is already working

- **Clean upward trajectory:** Infinite → Walmart → BlackBuck → PayPay, no unexplained gaps.
- **Scale signals are concrete:** "70M+ users" and "SEV1 services" are the kind of specifics
  that survive a 30-second recruiter skim.
- **Best line in the document** is the Walmart bullet: *reducing debugging time from 10 min to 1 sec*.
  Measurable, memorable, and it implies the problem without needing setup. Every other impact
  bullet should aim for this shape.
- **Ownership language is consistent:** tech owner, PIC, POC, interviewer. This is the main thing
  that separates an SE-III profile from an SE-II one, and it reads clearly.

---

## Highest-impact fixes

Ordered by how much damage each one does.

### 1. "current organisation" appears 4× under BlackBuck — a past employer

The single most damaging item in the file. It is a copy-paste artifact, and a reader interprets
it as carelessness rather than as a typo. Replace with "BlackBuck", or simply drop the phrase.

```text
before:  Developed the payment revamp as individual contributor in current organisation
after:   Developed the payment revamp as individual contributor
```

### 2. `Software Engineer - lll` uses three lowercase L's, not `III`

Appears in both the headline and the PayPay job title. Renders as a visibly wrong glyph
sequence in most fonts.

### 3. "7+ years" undersells the record

Aug 2018 → present is ~8 years (95 months across the four roles). Say **8+ years**.
Rounding *down* on a profile is a self-inflicted wound.

### 4. Unquantified claims — REWRITTEN, see section below

Two PayPay bullets hedged with "huge impact" and "significantly reduce costs". Numbers have now
been supplied; replacement copy is in **[Rewritten PayPay bullets](#rewritten-paypay-bullets)**.

| Current phrasing | Status |
|---|---|
| "...huge impact on the paypay eco-system" (client migration bullet) | Rewritten — bullet 1 below |
| "significantly reduce costs at the organization level" (New Relic → Grafana) | Rewritten — bullet 2 below |

**One real number beats three "significantly"s.**

### 5. The summary buries its own lede

It opens with `About me - https://...` — a URL that already appears in the contact block. The most
valuable real estate on the profile points elsewhere. Lead with the strongest claim instead:
8 years backend, SEV1 payment services at 70M+ user scale.

Also: the summary is currently one dense block. Break it into 2–3 short paragraphs or bullets.

### 6. Listing Cursor / Claude / ChatGPT / Gemini as skills reads thin — ADDRESSED by bullet 5

"Uses AI extensively" is a claim every candidate now makes, so it carries no signal on its own.
If it is genuinely differentiating, state the *outcome* rather than the tool names.

Drafted as **[bullet 5](#bullet-5--new-ai-assisted-engineering-workflow)** below, built on the actual
agent-tooling artifacts in `dab-core-payout-crux` rather than on tool names.

---

## Two structural observations

### Competitive programming is over-weighted for the target level

Five separate mentions: 200+ HackerEarth, 500+ LeetCode, 3-star CodeChef, Code Gladiator 2019,
plus "consistently participates". This is a strong signal for SE-I/SE-II and a
neutral-to-slightly-odd one for SE-III and above, where the question shifts to scope and
ownership. **Compress to one line** and reclaim the space for impact bullets.

### Current work is invisible — ADDRESSED by bullet 4

Nothing in the PayPay bullets mentions the data-platform side — the payout ETL, silver/gold
layer modelling, Databricks pipelines. The summary name-drops Databricks, but the experience
section stops at services and monitoring. That is a real, recent scope expansion going
unclaimed, and it is the strongest available evidence of growth *within* the current role.

Drafted as **[bullet 4](#bullet-4--new-payout-gold-data-layer-dab-core-payout-crux)** below.

---

## Rewritten PayPay bullets

Numbers supplied 2026-08-06/07. Bullets 1–2 replace the two hedged bullets; bullets 3–4 are new.

| # | Achievement | Headline result |
|---|---|---|
| 1 | Legacy fund monolith → top-up / P2P / withdraw microservices | Monolith decommissioned, ~$100K/yr saved, zero user impact at 60M+ users |
| 2 | New Relic → Grafana monitoring migration | Full alerting stack moved, New Relic licensing cost eliminated |
| 3 | Consumer payout report query optimisation | ~2 hrs → under 30 min (5x), accounting sign-off |
| 4 | Payout silver/gold layer on Databricks *(live in prod; clients migrating)* | Decouples clients from storage (unblocks DynamoDB → Aurora); pre-joined tables cut operational time ~20% |
| 5 | AI-assisted engineering workflow | Daily agent use + authored the repo's agent operating manual (weakest bullet — place last) |

Together these cover backend architecture (1), platform/observability (2), performance (3), data
engineering (4), and engineering leverage (5) — a deliberate spread rather than variations on one
theme. Note that 1, 3, and 4 are all storage/platform migrations; see the theme note under bullet 4.

### Bullet 1 — replaces the client-migration bullet

**Current text on the profile:**

> Worked as tech owner for migrating the clients from legacy monolitic system to new micro services
> and led the smooth migration for the major clients which has a huge impact on the paypay
> eco-system.

**What actually happened:** the legacy monolithic fund system was replaced by new top-up, P2P, and
withdraw microservices. You owned migrating every **upstream** client off the monolith onto those
services, which is what made it possible to **decommission the legacy monolith** — and that
decommission is the source of the **~$100K/year (USD)** saving. Delivered with no user-facing
impact at 60M+ user scale.

**Context added 2026-08-07:** you were in the payment fund team, which owned top-up, P2P, and
withdraw, and you **owned the withdraw service** — the money-out path from PayPay wallet to a user's
bank account. So this migration was your own service's clients, not a project you assisted with. Say
so; it converts "tech owner for a migration" into "service owner who led its migration".

**Replacement:**

> Owned the withdraw service (PayPay wallet to users' bank accounts) in the payment fund team and
> served as tech owner for migrating all upstream clients off the legacy monolithic fund system onto
> new top-up, P2P, and withdraw microservices, enabling full decommission of the monolith — completed
> with zero user-facing impact across 60M+ users and ~$100K/year in savings.

If that runs long, split the service ownership into its own bullet:

```text
- Owned the withdraw service in PayPay's payment fund team, handling money-out from
  user wallets to bank accounts for a platform serving 60M+ users.
- Tech owner for migrating all upstream clients off the legacy monolithic fund system
  onto new top-up, P2P, and withdraw microservices, enabling full decommission of the
  monolith with zero user-facing impact and ~$100K/year in savings.
```

Alternative phrasings, if a different emphasis is wanted:

```text
outcome-first:
Decommissioned the legacy monolithic fund system by migrating all upstream clients
onto new top-up, P2P, and withdraw microservices — zero user-facing impact across
60M+ users, ~$100K/year saved.

two bullets, if the client migration and the decommission deserve separate lines:
- Tech owner for migrating all upstream clients off the legacy monolithic fund
  system onto new top-up, P2P, and withdraw microservices, completing the cutover
  for every major client with zero user-facing impact across 60M+ users.
- Drove the full decommission of the legacy monolith once the last client was
  migrated, saving ~$100K annually.
```

What changed from the original, and why:

- **"huge impact on the paypay eco-system" → the decommission plus the numbers**. The original
  asserts impact; the replacement demonstrates it. "Huge impact" is the reader's conclusion to
  draw, not yours to claim.
- **Added "enabling full decommission of the monolith"** — this is the load-bearing clause. It
  names the *result* of the migration rather than just the activity, and it explains where the
  $100K came from. Without it, "migrated clients between services" sounds like maintenance work.
  With it, the bullet has a beginning and an end.
- **"new micro services" → "new top-up, P2P, and withdraw microservices"**. Naming the three
  domains turns an abstract phrase into a verifiable one. Also fixes `micro services` → one word.
- **"smooth migration" → "zero user-facing impact"**. *Smooth* is self-assessment; *zero
  user-facing impact* is a measurable outcome. On payment flows at this scale it is also the
  hardest part of the job, and the detail most likely to get cut while editing. Keep it.
- **"Worked as tech owner" → "Tech owner for"**. *Worked as* is filler that weakens the ownership
  claim; lead with the role.
- **"the clients" → "all upstream clients"**. Completeness is the achievement — a single unmigrated
  client keeps the monolith alive and cancels the entire saving.
- `monolitic` → `monolithic` (also in the copy-edit checklist).

**Currency: USD**, confirmed 2026-08-06. Keep the `$` symbol explicit rather than writing a bare
"100K", since a reader in Tokyo would otherwise default to yen.

**Causal chain is intact — no interview exposure here.** A decomposition on its own usually
*raises* infra cost, which would invite "how did adding three services save money?" This bullet
forecloses that: the saving is attributed to retiring the monolith, not to the split. Keep the
decommission clause and the $100K figure in the same sentence so the causality stays visible.

Note: profile summary says 70M+ users. 60M+ here is correct if that was the count *at migration
time* — the bullet is phrased so the two figures do not look like a contradiction.

### Bullet 2 — replaces the New Relic → Grafana bullet

> Drove my team's contribution to a company-wide New Relic → Grafana migration, taking end-to-end
> ownership of moving the full alerting system to Grafana and eliminating the team's New Relic
> licensing cost.

Rationale for each choice:

- **"Drove my team's contribution to a company-wide initiative"** — more credible than implying
  ownership of the whole org-wide program, and it still reads as ownership. Overclaiming here is
  the kind of thing that unravels under interview questioning.
- **"the full alerting system"** — the completeness *is* the achievement. A half-migrated
  alerting stack is worse than not starting.
- **"eliminating the team's New Relic licensing cost"** — concrete without inventing a figure.
  If a number can be attributed to the team's slice specifically, put it here instead.
- Consider adding **"with no gap in alert coverage"** if accurate. It is the monitoring
  equivalent of "zero user impact" and shows the risk was understood.

### Bullet 3 — NEW: consumer payout report query optimisation

Not currently on the profile. Source: *Consumer Payout Query Execution Improvement* design doc
(PP-327691), shipped ahead of the June 2025 monthly report.

The work: monthly consumer payout accounting reports regressed from ~25 min to 1.5–2 hrs after the
TiDB → Aurora migration. Diagnosed the cause, added a composite index on
`(type, state, source_account_type, processed_at)` plus a narrowing predicate, proved no data loss
against the original query, and got accounting sign-off before rolling it into the production
report.

**Replacement:**

> Diagnosed and fixed a 5x regression in monthly consumer payout accounting reports following a
> TiDB → Aurora migration, cutting execution from ~2 hrs to under 30 min via composite indexing and
> query redesign — validated for zero data loss and signed off by the accounting team before rollout.

Alternative phrasings, if a different emphasis is wanted:

```text
number-first:
Cut monthly consumer payout accounting report execution from 2h37m to 26m (5x) by
diagnosing a post-Aurora-migration query regression and introducing a composite
index, validated row-for-row against the original query and signed off by the
accounting team.

cross-functional emphasis:
Owned the fix for a 5x slowdown in monthly consumer payout accounting reports after
a TiDB to Aurora migration; restored sub-30-minute execution, proved output parity
with the legacy query, and secured accounting-team sign-off before production rollout.
```

Why this phrasing:

- **"5x" and "~2 hrs to under 30 min"** — both framings in one line: the ratio for skimmers, the
  absolute numbers for anyone who wants to sanity-check it. Feb 1h56m → 27m49s and Mar 2h37m →
  26m11s both support "5x" without rounding in your favour.
- **"Diagnosed and fixed"** — the diagnosis is half the work here. The regression came from a
  migration someone else did; finding that the missing index was the cause is the engineering.
- **"validated for zero data loss"** — this is a financial report. Any correctness claim about
  accounting output needs to be visible, and it shows you understood what was actually at stake.
- **"signed off by the accounting team"** — keep this. It is the only cross-functional
  stakeholder-management signal across all three bullets, and at SE-III+ that reads as strongly as
  the technical result.
- **Naming TiDB → Aurora** anchors the story: this was not a slow query someone wrote badly, it was
  a migration regression. It also puts two named datastores in front of a keyword scanner.

**Accuracy note — do not claim the query rewrite drove the win.** The doc's April test is the only
one holding the index constant (28m44s original vs 27m28s modified, both post-index), so the
composite index accounts for ~4x and the query modification for ~1.05x. The bullet above credits
"composite indexing and query redesign" jointly, which is accurate. Do not tighten it to imply the
rewrite was the primary lever — that claim would not survive someone reading the design doc.

### Bullet 4 — NEW: payout gold data layer (dab-core-payout-crux)

Not currently on the profile. This is the "current work is invisible" gap called out earlier —
the summary name-drops Databricks but no experience bullet covers the data-platform scope.

Sources: PP-406201 ticket scope (supplied 2026-08-07) + `dab-core-payout-crux` git history. The
Jira URL itself is not reachable from here (internal host, auth-gated), so repo figures below were
verified directly from git.

**The actual point of the project — lead with this, not the pipelines.** Payout is moving from
DynamoDB to Aurora MySQL. Downstream consumers currently read legacy sources directly (DynamoDB
tables, service-internal stores, ad-hoc datasets), so any storage change breaks them. The silver
tables in Databricks are an *abstraction layer*: once every client reads from them, payout can
swap its persistence layer without touching a single consumer. The ETL and views are the mechanism;
decoupling is the deliverable.

**Second, distinct value proposition — the pre-joined transaction × release × payout tables.**
Separate from decoupling, this eliminated repeated work for every consumer:

- Joining release/payout to transaction required substantial parsing logic on the client side.
  Verified in repo: `release_daily_release_keys` explodes `daily_release_ids` and parses encoded
  DynamoDB GSI keys of the form `{merchant_id}#{date_part1}|{date_part2}#{service_type}` through
  nested `SPLIT` chains to recover `(daily_release_date, service_type)` before any join is possible.
  Every consumer was reimplementing that.
- Some common metadata was not fully reflected on the transaction table, forcing consumers to join
  against the acquiring side to get it.
- Exposing raw schemas to clients made schema changes risky — the same coupling problem as above,
  in a second form.
- **Measured result: ~20% reduction in operational time**, plus removal of the recurring compute
  cost of re-running those joins.

This is the source of the only hard number available for this bullet. Use it.

**Verified from git, 2026-08-07:**

| Fact | Value |
|---|---|
| Your commits in repo | 266 (joint top contributor of 16 authors) |
| Active period | 2026-02-19 → present, ongoing |
| Originated the gold view layer | commit `b6c3c44`, 2026-06-09 — first 5 gold views |
| Gold views in repo today | 26 Databricks, 13 Athena, 8 Snowflake |
| Silver ETL pipelines (`ops_config/`) | 57 commits |
| Silver table schemas (`metadata/`) | 39 commits |
| Integration tests | 41 commits |

**Project status (updated 2026-08-07): shipped to PROD.** STG delivered Q4, PROD silver tables and
gold views are live, and downstream clients are actively migrating onto them. So the build is past
tense; only the client migration is still in flight. Phrasing below reflects that split.

**Replacement:**

> Delivered PayPay's payout silver/gold data layer on Databricks — now the standard consumption
> interface for downstream payout clients, decoupling dashboards, pipelines, reports, and
> reconciliations from the underlying store so payout can migrate DynamoDB → Aurora MySQL without
> breaking consumers. Replaced client-side join and key-parsing logic with pre-joined
> transaction × release × payout datasets, cutting operational time ~20% and the recurring cost of
> those joins; clients are actively migrating onto the new gold views.

Alternative phrasings, if a different emphasis is wanted:

```text
outcome-first:
Built and shipped the abstraction layer that unblocks PayPay's payout DynamoDB →
Aurora migration: Databricks silver/gold datasets, live in production, as the single
consumption interface for downstream clients — replacing direct reads and hand-rolled
join logic with governed contracts carrying freshness SLAs, cutting operational time
~20% and eliminating repeated join cost.

shorter, if the bullet needs trimming:
Shipped the payout silver/gold data layer on Databricks to production as the standard
consumption interface for downstream clients, decoupling them from payout's storage
ahead of a DynamoDB → Aurora MySQL migration; pre-joined transaction × release × payout
datasets cut operational time ~20% and removed recurring join cost.

two bullets, if both value propositions deserve their own line:
- Delivered the payout silver/gold data layer on Databricks as the single consumption
  interface for downstream clients, decoupling dashboards, pipelines, reports, and
  reconciliations from payout's storage so a DynamoDB to Aurora MySQL migration can
  ship without breaking consumers; published table contracts, freshness SLAs, and
  automated parity checks.
- Designed pre-joined transaction x release x payout datasets that replace the DynamoDB
  GSI key parsing and multi-source join logic every consumer previously reimplemented,
  reducing operational time ~20% and the recurring compute cost of those joins.
```

Why this phrasing:

- **Lead with decoupling, not with pipelines.** "Built ETL pipelines and views" describes output
  and reads as routine data engineering. "Built the abstraction layer that lets us swap the
  database under a live payment system" describes *architecture* — same work, and it is the framing
  that matches an SE-III+ scope. This is the single most important choice in the bullet.
- **"DynamoDB → Aurora MySQL"** — names the migration this enables. Without it the reader has no
  reason to care that consumers were decoupled; with it, the bullet has a why.
- **"dashboards, pipelines, reports, and reconciliations"** — straight from the ticket's client
  inventory. Concrete consumer types show the blast radius was actually mapped, not hand-waved.
- **"published table contracts, freshness SLAs, and automated parity checks"** — these are the
  governance artifacts from the success criteria, and they are what separates a platform project
  from a pile of tables. `docs/dataset-mapping.md` in the repo is exactly this contract (per-dataset
  SLA, e.g. transaction gold by 6AM JST next day), so the claim is backed.
- **"~20%" is the only hard number here — keep it.** It attaches to the pre-joined tables
  specifically (operational time saved by consumers no longer writing join and key-parsing logic).
  Do not restate it as a platform-wide or cost figure.
- **"pre-joined transaction × release × payout"** — names the actual datasets and makes the 20%
  legible. "Improved data access" would not.
- **Do not lead with "26 gold views"** — a count of files is an activity metric. It belongs in an
  interview answer, not in the bullet.
- **Still no DynamoDB cost figure.** The ticket lists reduced DynamoDB reads and fewer GSIs as a
  *benefit*, not a measured result. Do not conflate that with the 20% operational-time figure, which
  is measured and separate. If a read-cost reduction gets measured after the client migration
  completes, add it then.

**Tense note — delivered, with migration ongoing.** The layer itself is shipped to production, so
lead with **"Delivered"** / **"Shipped"** rather than "Building": a completed deliverable reads far
stronger than work in progress, and it is accurate. Keep the client migration in the present
("clients are actively migrating") — that is honest and it also signals adoption is underway rather
than hypothetical.

Two things to add once the migration finishes, both of which would strengthen the bullet materially:

1. **Number of client workloads migrated** — "migrated N downstream consumers" turns adoption from a
   claim into a count.
2. **Measured DynamoDB read-cost reduction**, once legacy read paths are disabled.

Until then, do **not** write "migrated all clients" or "all consumers now read from silver" — that is
the one overclaim available here, and it is the success criterion still open.

**A theme worth naming in interviews, though not on the profile.** Bullets 1, 3, and 4 are all
storage/platform migrations: monolith → microservices, a TiDB → Aurora regression fix, and now the
decoupling layer for DynamoDB → Aurora. That is a coherent specialism — *safely changing the
foundations underneath live payment systems* — and it is a stronger narrative than four unrelated
wins. Consider using it as the spine of the summary rewrite (step 4 in the work order).

**Attribution caution — worth being precise about in interviews.** `git log --diff-filter=A` credits
all 26 current view files to a colleague (KhiemNS), because the originals you created in `b6c3c44`
were later renamed to the `NN_payout_gold_*` scheme and substantially expanded. `--follow` confirms
your commits are the root of that lineage. So "built the payout silver/gold layer / co-own the
payout data platform" is accurate; "authored all 26 gold views" is not. That repo has 266 commits
from you and 260 from KhiemNS — genuinely shared ownership, and phrasing it that way is both honest
and still strong.

**Attribution caution — worth being precise about in interviews.** `git log --diff-filter=A` credits
all 26 current view files to a colleague (KhiemNS), because the originals you created in `b6c3c44`
were later renamed to the `NN_payout_gold_*` scheme and substantially expanded. `--follow` confirms
your commits are the root of that lineage. So: "built the gold view layer / co-own the payout data
platform" is accurate; "authored all 26 gold views" is not. That repo has 266 commits from you and
260 from KhiemNS — it is genuinely shared ownership, and phrasing it that way is both honest and
still strong.

### Bullet 5 — NEW: AI-assisted engineering workflow

This **replaces** the "Cursor / Claude / ChatGPT / Gemini" skills listing flagged in
[fix 6](#6-listing-cursor--claude--chatgpt--gemini-as-skills-reads-thin--addressed-by-bullet-5).
Naming four tools claims nothing — every candidate now writes "uses AI extensively". The
differentiating claim is narrower and verifiable: you work daily with coding agents on a
payment-domain codebase and wrote the instruction layer that makes them produce correct changes there.

**Scope confirmed 2026-08-07: heavy user, not platform builder.** This matters — an earlier draft of
this bullet overclaimed and had to be cut back. See the attribution note below before using it.

**Verified from git, 2026-08-07:**

| Artifact | Authorship |
|---|---|
| `AGENTS.md` in `dab-core-payout-crux` (26 KB) | Scaffolded by Codex Bot 2025-12-15; **+1360/−110 lines from you across 9 commits** — architecture, environments, deployment flow, schema conventions, troubleshooting. Substantially yours. |
| `docs/skills/payout-gold-query-migration/` (SKILL.md + 13 table docs) | Created by KhiemNS 2026-07-03. Your involvement is incidental (+101/−100, schema-change follow-through). **Not yours to claim.** |
| `docs/superpowers/` plans & specs | KhiemNS. No contribution from you. **Not yours to claim.** |
| `isengard` (PayPay's multi-tenant AI builder platform — Slack agents, MCP sub-agents, tool marketplace, coding-agent-runner) | Khiem Nguyen, 562 commits. **You have 4**, all 2026-03-30 doc fixes to `SokAskHandler`/`SokQueryHandler`. **Not yours to claim.** |

**Replacement:**

> Work daily with AI coding agents on a payment-domain codebase, authoring the repo's agent
> operating manual — architecture, schema conventions, deployment flow, and domain guardrails — so
> agents produce correct changes against payout schemas rather than plausible-looking ones.

Alternative phrasings, if a different emphasis is wanted:

```text
leverage-first:
Use AI coding agents as a daily force multiplier on payout data engineering work, and
maintain the repo-level agent instructions and domain guardrails that make their output
correct rather than merely plausible.

adoption emphasis:
Early and heavy adopter of AI coding agents within the payout team, contributing the
agent instruction layer for the payout data repo (architecture, environments, schema
conventions, troubleshooting) that agents and new engineers both work from.
```

Why this phrasing:

- **"correct changes rather than plausible-looking ones"** — this is the real insight and the reason
  the bullet is worth including. Anyone can get an agent to emit SQL; getting it to respect payout's
  schema conventions is the actual problem, and naming it shows you have used these tools seriously
  rather than superficially.
- **"authoring the repo's agent operating manual"** — defensible at +1360 lines. It also happens to
  serve human onboarding, which is worth saying out loud in an interview.
- **No tool names in the bullet.** Cursor/Claude/ChatGPT/Gemini can stay in the Skills section, but
  they should not carry the claim. Tools change; the practice transfers.
- **No productivity multiplier.** Resist "3x faster" — unmeasured and instantly discounted.
- **Weakest of the five bullets.** It is a practice, not a shipped outcome with a number. Place it
  last, or fold it into the summary as a single clause. Do not lead with it.

**Attribution correction — read this before publishing.** An earlier draft of this bullet claimed you
"built the AI tooling layer… a packaged reusable migration skill with scoped read-only database
access and a mandatory human review gate." Git shows that skill was created by KhiemNS, and
`isengard` is Khiem Nguyen's platform (562 commits vs your 4 documentation fixes). Those claims
described someone else's work and would not survive an interview question like "walk me through how
you designed the review gate." What is genuinely yours is the `AGENTS.md` instruction layer and
sustained, effective daily use of agents on a domain codebase — which is what the bullet above says.

**Do not claim:** building isengard or the agent platform; authoring the payout-gold migration skill;
that AI wrote the payout ETL or gold layer.

### Confirmed: independent projects — keep the numbers separate

Verified 2026-08-06/07 — the ~$100K/year saving comes from decommissioning the legacy fund monolith
(bullet 1) and is **not** the New Relic → Grafana saving (bullet 2). Bullets 3, 4, and 5 are further
independent workstreams. Keep all five distinct; do not merge, and do not let figures drift between
them: `~$100K/yr` belongs only to bullet 1, `5x` / `2 hrs → 30 min` only to bullet 3, and `~20%`
only to the pre-joined tables in bullet 4. Bullet 5 carries no number by design.

### Dropped

The third hedge, "significantly reduce infra costs", is intentionally removed rather than
rewritten. No number available and the point is already carried by the bullets above.

---

## Rewritten summary (About section)

Drafted 2026-08-07. Replaces the current summary, which opens with an `About me - https://...` URL
and runs as one dense block. LinkedIn allows 2,600 characters; this uses ~1,450, which is
deliberate — the About section is skimmed, not read.

### Primary version — use this

> Backend engineer with 8+ years building payment systems, currently Software Engineer III at PayPay
> in Tokyo. I've spent my time here on the money-out side of payments: first in the payment fund team,
> where I owned the withdraw service that moves funds from PayPay wallets into users' bank accounts,
> and now on merchant payout — aggregating everything 70M+ users pay a merchant and settling that
> total into the merchant's bank account.
>
> My work is mostly one thing: changing the foundations underneath live payment systems without
> anyone noticing. As owner of withdraw, I migrated every upstream client off a legacy monolithic fund
> system onto new top-up, P2P, and withdraw microservices, which let us decommission the monolith and
> save ~$100K a year — with zero user-facing impact. I diagnosed and fixed a 5x regression in monthly
> merchant settlement reports after a TiDB to Aurora migration, cutting runtime from ~2 hours to under
> 30 minutes. Most recently I delivered PayPay's payout silver/gold data layer on Databricks, which
> decouples every downstream consumer from our storage so we can move from DynamoDB to Aurora without
> breaking anyone; its pre-joined datasets cut consumers' operational time by ~20%.
>
> Day to day I work in Kotlin, Java, and Spring Boot across Kafka, Aurora, DynamoDB, TiDB, AWS, and
> Databricks/Spark, and I care a lot about observability — I led my team's move from New Relic to
> Grafana. I also work daily with AI coding agents and maintain the agent instruction layer for our
> payout data repo, so their output respects our schema conventions instead of just looking
> plausible.
>
> Underneath all of it is a habit of practising fundamentals: 500+ problems on LeetCode, 200+ on
> HackerEarth, 3-star on CodeChef. It is why I reach for the right data structure before reaching
> for more infrastructure.

### Shorter version — if the above feels long

> Software Engineer III at PayPay (Tokyo), 8+ years in backend and 3+ on the money-out side of
> payments — first owning the withdraw service (PayPay wallet to users' bank accounts) in the payment
> fund team, now owning merchant payout: aggregating what 70M+ users pay a merchant and settling it
> into their bank account.
>
> I specialise in changing the foundations under live payment systems safely: as withdraw owner,
> migrating all upstream clients off a legacy fund monolith so we could decommission it (~$100K/year
> saved, zero user impact); fixing a 5x regression in monthly settlement reports after a TiDB to
> Aurora migration (~2 hrs → under 30 min); and delivering the payout silver/gold data layer on
> Databricks that decouples every downstream consumer from our storage ahead of a DynamoDB to Aurora
> move.
>
> Kotlin, Java, Spring Boot, Kafka, Aurora, DynamoDB, AWS, Databricks/Spark. Strong on
> observability — led my team's New Relic to Grafana migration. Daily user of AI coding agents, and I
> maintain the agent instruction layer that keeps their output correct in our payout repo. 500+
> LeetCode / 200+ HackerEarth solved; I still practise fundamentals.

### Why it is built this way

- **Opens with the claim, not a URL.** The current summary's first line is `About me - https://...`,
  spending the most-read sentence on a link already in the contact block. First line now carries
  years, level, domain, and scale.
- **"Payout" is explained, not assumed.** *Merchant payout = aggregating everything 70M+ users pay a
  merchant and settling that total into the merchant's bank account.* A recruiter or hiring manager
  outside payments does not know what "payout services" means, and the plain-English version is far
  more impressive: it is money movement into real bank accounts, which conveys correctness stakes
  that jargon hides. Keep this sentence even if the summary gets cut elsewhere.
- **Scale attributed correctly.** Payout serves *merchants*; the 70M+ users are the source of the
  funds flowing through it. Phrase it as "what 70M+ users pay a merchant", not "payout serves 70M+
  users" — the latter is imprecise and a domain interviewer would notice.
- **The internal move is now the narrative, and it is a strong one.** Payment fund team (owned
  **withdraw** — PayPay wallet → user's bank account) → merchant payout (merchant's bank account).
  Both are money-out; the second is the same problem at aggregate scale with accounting and
  settlement obligations attached. That reads as deliberate progression within one specialism rather
  than a team reshuffle, and it is far better than listing two unrelated teams. It also removes the
  need to explain why the profile shows one employer for 3+ years.
- **"As owner of withdraw, I migrated…" repositions bullet 1.** Previously the monolith
  decomposition could be read as work you happened to be near. Naming yourself as owner of one of the
  three services that came out of it makes the ownership claim concrete and the client migration a
  natural consequence of that role.
- **"Accounting reports" → "merchant settlement reports".** Same reasoning: names what the report is
  actually for. (In the bullet-3 copy, "accounting" stays accurate where the accounting team's
  sign-off is the point.)
- **"8+ years", not "7+".** Aug 2018 → now is ~8 years. See [fix 3](#3-7-years-undersells-you).
- **One spine, three proofs.** *"Changing the foundations underneath live payment systems without
  anyone noticing"* is the thread through bullets 1, 3, and 4 — otherwise they read as three
  unrelated wins. A reader remembers one positioning statement; they will not remember four.
- **Numbers in the middle paragraph, not the first.** Leading with figures reads as a résumé dump;
  leading with the positioning statement earns them.
- **Grafana demoted to a clause.** Bullet 2 is real but the weakest of the four, and it fits
  naturally as evidence for caring about observability rather than as its own sentence.
- **AI as a practice, not a tool list.** "Work daily with AI coding agents and maintain the agent
  instruction layer… so their output respects our schema conventions instead of just looking
  plausible" — a claim only someone with real usage would make. No tool names, no "3x faster".
- **Competitive programming last, one line, with a reason.** You asked for it included, so it is in —
  but note the earlier finding that it is over-weighted for SE-III+ ([see structural
  observation](#competitive-programming-is-over-weighted-for-the-target-level)); the profile currently
  mentions it five times. One line closing on *why* it matters ("the right data structure before more
  infrastructure") converts it from a junior-candidate signal into a stated engineering value. If you
  are targeting Staff, cut this paragraph; for SE-III/Senior roles it reads fine.
- **First person, contractions avoided, no buzzwords.** No "passionate", no "results-driven".

### Do not add

- A productivity multiplier for the AI paragraph.
- The DynamoDB read-cost saving (not yet measured — see bullet 4).
- "Migrated all clients" for the gold layer (still in flight).
- The $100K figure anywhere except the monolith decommission.

---

## Copy-edit checklist

| Issue | Fix |
|---|---|
| `monolitic` | monolithic |
| `new relic` | New Relic |
| `kafka` | Kafka |
| `MYSQL` | MySQL |
| `Wire mock` | WireMock |
| `grafana` | Grafana |
| `kibana` | Kibana |
| `solr` | Solr |
| `Paypay` vs `PayPay` | Pick one — official styling is **PayPay** |
| `Java , Kotlin ,` | No space *before* a comma; applies throughout |
| `more than 200+` | Redundant — just `200+` |
| `used by cross within Walmart` | "used by other teams across Walmart" |
| `POC` | Ambiguous: point of contact, or proof of concept? Spell it out |
| Top Skills: Distributed Systems, TiDB, PromQL | Does not match the headline's Kotlin/Java emphasis — realign |

---

## Suggested order of work

1. Fix items 1–3 above (5 minutes, removes all the credibility hits).
2. Run the copy-edit checklist.
3. Paste in the five rewritten PayPay bullets. All five are final — no blanks left to fill.
4. Replace the Cursor / Claude / ChatGPT / Gemini skills line with bullet 5.
5. Replace the About section with the [rewritten summary](#rewritten-summary-about-section).
6. Compress the competitive-programming mentions in the experience section to a single line (the
   summary now carries it once — five mentions across the profile is four too many).

Every step is now copy-paste. Nothing left that needs fresh writing.
