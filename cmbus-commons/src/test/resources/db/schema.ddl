create schema if not exists CMD_MMSC authorization sa;

create table if not exists CMD_MMSC.CAMPAIGN_STEPS (
  ID              varchar2(32)  not null,
  STEP_NO         number(3,0)   not null,
  CYCLE_NO        number(3,0)   not null,
  INIT_TIMESTAMP  datetime      not null,
  SMIL_URL        varchar2(64)  not null
);

create table if not exists  CMD_MMSC.CAMPAIGN_RECIPIENTS (

  ID                            number(7,0)   not null,
  CAMPAIGN_STEP_ID              varchar2(32)  not null,
  CAMPAIGN_STEP_STEP_NO         number(3,0)   not null,
  CAMPAIGN_STEP_CYCLE_NO        number(3,0)   not null,
  MSISDN                        char(11)     not null,
  PARAM0                        varchar2(64),
  PARAM1                        varchar2(64),
  PARAM2                        varchar2(64),
  PARAM3                        varchar2(64),
  PARAM4                        varchar2(64),
  PARAM5                        varchar2(64),
  PARAM6                        varchar2(64),
  PARAM7                        varchar2(64),
  PARAM8                        varchar2(64),
  PARAM9                        varchar2(64)
);
