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
  MSISDN                        char(11)      not null,
  PARAM00                        varchar2(2000),
  PARAM01                        varchar2(2000),
  PARAM02                        varchar2(2000),
  PARAM03                        varchar2(2000),
  PARAM04                        varchar2(2000),
  PARAM05                        varchar2(2000),
  PARAM06                        varchar2(2000),
  PARAM07                        varchar2(2000),
  PARAM08                        varchar2(2000),
  PARAM09                        varchar2(2000),
  PARAM10                        varchar2(2000),
  PARAM11                        varchar2(2000),
  PARAM12                        varchar2(2000),
  PARAM13                        varchar2(2000),
  PARAM14                        varchar2(2000),
  PARAM15                        varchar2(2000),
  PARAM16                        varchar2(2000),
  PARAM17                        varchar2(2000),
  PARAM18                        varchar2(2000),
  PARAM19                        varchar2(2000)
);
