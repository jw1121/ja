-- check should insert payload.
Select 'OWNDAT' as tblname, own1 as oldown from test.OWNDAT
Where PARID = ? and TAXYR = ?

Select 'OWNMLT' as tblname from test.OWNMLT
Where PARID = ? and TAXYR = ? and OWNSEQ = ?
