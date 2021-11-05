
--drop database QUANLYTIENDIEN
use QUANLYTIENDIEN
go

insert TAIKHOAN
values('admin','123',null,null)
go

insert into HOTIEUTHU
values('KH001',N'Trần Hưng Đạo','32600012',N'Nam Dinh',N'Nam','12/09/1997','0954755862','06/06/2019',N'Sinh hoạt'),
	  ('KH002',N'Vũ Tuấn Anh','32204029',N'Thai Binh',N'Nam','11/23/2000','0346864934','05/11/1998',N'Hành chính'),
	  ('KH003',N'Lê Trọng Tấn','84234756',N'Ha Noi',N'Nam','02/26/1969','0978371315','10/12/1995',N'Kinh doanh'),
	  ('KH004',N'Nguyễn Phương Dung','26468003',N'Hue',N'Nu','11/15/1984','0974911469','03/17/2001',N'Sinh hoạt'),
	  ('KH005',N'Trần Văn Trà','37018297',N'Quang Ngai',N'Nam','04/26/1977','0918614525','12/21/1998',N'Sản xuất'),
	  ('KH006',N'Bùi Tiến Dũng','83991236',N'Ha Noi',N'Nam','06/22/1980','0946999935','07/06/2001',N'Hành chính'),
	  ('KH007',N'Nguyễn Kim Thành','55315356',N'Hue',N'Nam','03/29/1979','0989048777','02/09/2002',N'Sinh hoạt'),
	  ('KH008',N'Lê Tú Trinh','24246961',N'Ha Noi',N'Nam','01/07/1982','0843120976','07/21/1999',N'Kinh doanh'),
	  ('KH009',N'Nguyễn Xuân Quỳnh','67896724',N'Hai Duong',N'Nam','06/10/1974','0976997092','02/18/2010',N'Kinh doanh'),
	  ('KH010',N'Bùi Đăng Đoàn','63940570',N'Ha Noi',N'Nam','08/11/1965','0343741618','07/07/2009',N'Hành chính'),
	  ('KH011',N'Dương Văn Hiên','10891455',N'Ninh Binh',N'Nu','12/28/1980','0987803648','08/03/2000',N'Sản xuất'),
	  ('KH012',N'Nguyễn An','15376771',N'Ha Tinh',N'Nam','10/09/1970','0358078887','06/15/2002',N'Sản xuất'),
	  ('KH013',N'Trần Thị Phượng','32600015',N'Tuyen Quang',N'Nam','12/09/1956','0954755858','06/06/2014',N'Sinh hoạt'),
	  ('KH014',N'Phạm Doãn Hiếu','32600016',N'Thai Binh',N'Nam','05/04/2000','0346864697','04/11/2013',N'Hành chính'),
	  ('KH015',N'Nguyễn Xuân Lộc','84234757',N'Ha Giang',N'Nam','04/29/1973','0978371436','09/16/1997',N'Kinh doanh'),
	  ('KH016',N'Trần Quang Huy','26468004',N'Lang Son',N'Nu','11/14/1956','0974956425','03/06/2011',N'Sinh hoạt'),
	  ('KH017',N'Hoàng Hữu Trượng','37018298',N'Quang Ngai',N'Nam','09/16/1974','091857210','02/18/2004',N'Sản xuất'),
	  ('KH018',N'Nguyễn Quang Ngọc','83991237',N'Quang Binh',N'Nam','02/02/1996','0946563105','07/06/2001',N'Hành chính'),
	  ('KH019',N'Nguyễn Thị Thanh Tâm','55315359',N'Phu Tho',N'Nam','03/04/1979','0989567128','02/09/2002',N'Sinh hoạt'),
	  ('KH020',N'La Thị Lan Anh','24246963',N'Vinh Phuc',N'Nam','01/12/1982','0843586146','07/21/1999',N'Kinh doanh'),
	  ('KH021',N'Trần Thị Quỳnh','67898724',N'Hai Duong',N'Nam','05/11/1989','0256879354','02/18/2007',N'Kinh doanh'),
	  ('KH022',N'Nịnh Thị Hiền','63940560',N'Ca Mau',N'Nam','08/11/1978','0369725481','07/06/2006',N'Hành chính'),
	  ('KH023',N'Đào Thị Hoài Thư','10591455',N'Nghe An',N'Nu','10/25/1979','0987562047','08/09/2004',N'Sản xuất'),
	  ('KH024',N'Nguyễn Ngọc Giản','15372771',N'Thanh Hoa',N'Nam','10/09/1990','0358120379','06/19/2008',N'Sản xuất'),
	  ('KH025',N'Ngọc Minh Vũ','55315346',N'Hue',N'Nam','03/30/1986','0989048778','02/15/2002',N'Sinh hoạt'),
	  ('KH026',N'Ma Thị Hoài Thương','21246961',N'Ha Noi',N'Nam','01/07/1992','0843120651','07/29/1997',N'Kinh doanh'),
	  ('KH027',N'Trần Thị Khánh Huyền','67896624',N'Hai Duong',N'Nam','10/10/1994','024673585','02/25/2013',N'Kinh doanh'),
	  ('KH028',N'Bùi Việt Kiều','63940570',N'Ha Noi',N'Nam','05/16/1989','0343741126','01/01/2004',N'Hành chính'),
	  ('KH029',N'Âu Thị Na','10801455',N'Lao Cai',N'Nu','12/28/1995','0987856943','06/02/2006',N'Sản xuất'),
	  ('KH030',N'Mai An Tiêm','15306771',N'Yen Bai',N'Nam','05/06/1977','0358021057','06/24/2007',N'Sản xuất')

go

insert into THANG
values 
	  ('T012020',N'Thang 1/2020'),
	  ('T022020',N'Thang 2/2020'),
	  ('T032020',N'Thang 3/2020'),
	  ('T042020',N'Thang 4/2020'),
	  ('T052020',N'Thang 5/2020'),
	  ('T062020',N'Thang 6/2020'),
	  ('T072020',N'Thang 7/2020'),
	  ('T082020',N'Thang 8/2020'),
	  ('T092020',N'Thang 9/2020'),
      ('T102020',N'Thang 10/2020'),
	  ('T112020',N'Thang 11/2020'),
	  ('T122020',N'Thang 12/2020')
go

insert into HOADON
values('HD001','KH001',null,null),
	  ('HD002','KH005',null,null),
	  ('HD003','KH003',null,null),
	  ('HD004','KH002',null,null),
	  ('HD005','KH004',null,null),
	  ('HD006','KH012',null,null),
	  ('HD007','KH007',null,null),
	  ('HD008','KH006',null,null),
	  ('HD009','KH009',null,null),
	  ('HD010','KH008',null,null),
	  ('HD011','KH010',null,null),
	  ('HD012','KH011',null,null),
	  ('HD013','KH013',null,null),
	  ('HD014','KH014',null,null),
	  ('HD015','KH015',null,null),
	  ('HD016','KH016',null,null),
	  ('HD017','KH017',null,null),
	  ('HD018','KH018',null,null),
	  ('HD019','KH019',null,null),
	  ('HD020','KH020',null,null),
	  ('HD021','KH021',null,null),
	  ('HD022','KH022',null,null),
	  ('HD023','KH023',null,null),
	  ('HD024','KH024',null,null),
	  ('HD025','KH025',null,null),
	  ('HD026','KH026',null,null),
	  ('HD027','KH027',null,null),
	  ('HD028','KH028',null,null),
	  ('HD029','KH029',null,null),
	  ('HD030','KH030',null,null)

go

insert into CHISODIEN
values('KH001','T112020',3598,3700),
	  ('KH002','T112020',4633,4906),
	  ('KH003','T112020',1200,1250),
	  ('KH004','T112020',4001,5678),
	  ('KH005','T112020',3942,4507),
	  ('KH006','T112020',6045,7320),
	  ('KH007','T112020',5238,5500),
	  ('KH008','T112020',4675,4750),
	  ('KH009','T112020',5165,5616),
	  ('KH010','T112020',7496,8412),
	  ('KH011','T112020',1216,2015),
	  ('KH012','T112020',8104,8915),
	  ('KH013','T112020',3365,3652),
	  ('KH014','T112020',4512,4909),
	  ('KH015','T112020',1500,1569),
	  ('KH016','T112020',6598,6987),
	  ('KH017','T112020',4523,4958),
	  ('KH018','T112020',7842,7985),
	  ('KH019','T112020',5238,5558),
	  ('KH020','T112020',4675,4746),
	  ('KH021','T112020',5123,5584),
	  ('KH022','T112020',7264,8358),
	  ('KH023','T112020',2574,2968),
	  ('KH024','T112020',10256,10387),
	  ('KH025','T112020',5735,5840),
	  ('KH026','T112020',4624,4728),
	  ('KH027','T112020',5105,5630),
	  ('KH028','T112020',7428,8432),
	  ('KH029','T112020',1202,2005),
	  ('KH030','T112020',8109,8972)
go

insert into THONGKE
values('KH001','T112020','HD001',0),
	  ('KH002','T112020','HD004',0),
	  ('KH003','T112020','HD003',0),
	  ('KH004','T112020','HD005',0),
	  ('KH005','T112020','HD002',0),
	  ('KH006','T112020','HD008',0),
	  ('KH007','T112020','HD007',0),
	  ('KH008','T112020','HD010',0),
	  ('KH009','T112020','HD009',0),
	  ('KH010','T112020','HD011',0),
	  ('KH011','T112020','HD012',0),
	  ('KH012','T112020','HD006',0),
	  ('KH013','T112020','HD013',0),
	  ('KH014','T112020','HD014',0),
	  ('KH015','T112020','HD015',0),
	  ('KH016','T112020','HD016',0),
	  ('KH017','T112020','HD017',0),
	  ('KH018','T112020','HD018',0),
	  ('KH019','T112020','HD019',0),
	  ('KH020','T112020','HD020',0),
	  ('KH021','T112020','HD021',0),
	  ('KH022','T112020','HD022',0),
	  ('KH023','T112020','HD023',0),
	  ('KH024','T112020','HD024',0),
	  ('KH025','T112020','HD025',0),
	  ('KH026','T112020','HD026',0),
	  ('KH027','T112020','HD027',0),
	  ('KH028','T112020','HD028',0),
	  ('KH029','T112020','HD029',0),
	  ('KH030','T112020','HD030',0)
go

updateHoaDon 'kh001','T112020' --gọi thủ tục update data cho lượng tiêu thụ và tiền 
go
updateHoaDon 'kh002','T112020'
go
updateHoaDon 'kh003','T112020'
go
updateHoaDon 'kh004','T112020'
go
updateHoaDon 'kh005','T112020'
go
updateHoaDon 'kh006','T112020'
go
updateHoaDon 'kh007','T112020'
go
updateHoaDon 'kh008','T112020'
go
updateHoaDon 'kh009','T112020'
go
updateHoaDon 'kh010','T112020'
go
updateHoaDon 'kh011','T112020'
go
updateHoaDon 'kh012','T112020'
go
updateHoaDon 'kh013','T112020'
go
updateHoaDon 'kh014','T112020'
go
updateHoaDon 'kh015','T112020'
go
updateHoaDon 'kh016','T112020'
go
updateHoaDon 'kh017','T112020'
go
updateHoaDon 'kh018','T112020'
go
updateHoaDon 'kh019','T112020'
go
updateHoaDon 'kh020','T112020'
go
updateHoaDon 'kh021','T112020'
go
updateHoaDon 'kh022','T112020'
go
updateHoaDon 'kh023','T112020'
go
updateHoaDon 'kh024','T112020'
go
updateHoaDon 'kh025','T112020'
go
updateHoaDon 'kh026','T112020'
go
updateHoaDon 'kh027','T112020'
go
updateHoaDon 'kh028','T112020'
go
updateHoaDon 'kh029','T112020'
go
updateHoaDon 'kh030','T112020'
go

