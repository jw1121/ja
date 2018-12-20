-- check should insert payload.
Select 'OWNDAT' as tblname, own1 as oldown from test.OWNDAT
Where paid = ? and taxer = ?
Union
Select 'OWNMLT' as tblname from test.OWNMLT
Where paid = ? and taxer = ?
