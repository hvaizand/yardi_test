## Description

## Script
    //SQL
    INSERT INTO DatafixHistory(hyCRM, sTableName, hForeignKey, sNotes, sNewValue, sOldValue, dtDate)
    SELECT CHG#INC# 
        , 'Table name'
        , hMy 
        , 'Comment. Issue INCINC# - CHGCHG#)'
        , New Value
        , Old Value
        , GETDATE()
    FROM  Table name
    WHERE hMy   

    UPDATE trans 
    SET sTranFields2 = NULL
    WHERE hMy IN ()
    //END SQL

## Example
```sql
INSERT INTO DatafixHistory(hyCRM, sTableName, hForeignKey, sNotes, sNewValue, sOldValue, dtDate)
SELECT CHG#INC# 
  , 'Table name'
  , hMy 
  , 'Comment. Issue INCINC# - CHGCHG#)'
  , New Value
  , Old Value
  , GETDATE()
FROM  Table name
WHERE hMy   

UPDATE trans 
SET sTranFields2 = NULL
WHERE hMy IN ()
//END SQL
```
