create database QUANLYTIENDIEN
go

use quanlytiendien
go
--drop database QUANLYTIENDIEN

CREATE TABLE [dbo].[HOTIEUTHU](
	[maKH] [char](5) primary key,
	[hoTen] [nvarchar](50) NOT NULL,
	[CMND] [varchar](12) NOT NULL,
	[diaChi] [nvarchar](50) NULL,
	[gioiTinh] [nvarchar](3) NULL,
	[ngaySinh] [date] NULL,
	[sdt] [varchar](12) NULL,
	[ngayDangKi] [date] NULL,
	loaiDien nvarchar(20) not null
)
go

CREATE TABLE [dbo].[THANG](
	[maThang] [char](7) primary key,
	[tenThang] [nvarchar](20) NULL,
)
go

CREATE TABLE [dbo].[HOADON](
	[maHD] [char](10) NOT NULL,
	[maKH] [char](5) NOT NULL,
	[ldtt] [int] NULL,
	[tien] [money] NULL,
	primary key (maHD, maKH),
	constraint HOTIEUTHU_HOADON 
	foreign key (maKH) references HOTIEUTHU (maKH)
)
go

CREATE TABLE [dbo].[CHISODIEN](
	[maKH] [char](5) NOT NULL,
	[maThang] [char](7) NOT NULL,
	[chisocu] [int] NULL,
	[chisomoi] [int] NULL,
	primary key(maKH, maThang),
	constraint HOTIEUTHU_CHISODIEN
	foreign key (maKH) references HOTIEUTHU (maKH),
	constraint THANG_CHISODIEN
	foreign key (maThang) references THANG (maThang)
)
go

CREATE TABLE [dbo].[THONGKE](
	[maKH] [char](5 ) NOT NULL,
	[maThang] [char](7) NOT NULL,
	[maHD] [char](10) NOT NULL,
	payment bit default 0,
	primary key (maKH, maThang, maHD),
	constraint HOTIEUTHU_THONGKE
	foreign key (maKH) references HOTIEUTHU (maKH),
	constraint THANG_THONGKE
	foreign key (maThang) references THANG (maThang),
	constraint HOADON_THONGKE
	foreign key (maHD, maKH) references HOADON (maHD, maKH)
)
go

CREATE TABLE [dbo].[TAIKHOAN](
	[tai_khoan] [varchar](15) primary key,
	[mat_khau] [varchar](15) NOT NULL,
	cauhoi nvarchar(50) null,
	traloi nvarchar(50) null
)
go

create function getCSC(@maKH char(5), @maThang char(7))
returns int
as 
	begin
		declare @CSC int;
		select @CSC = chisocu from CHISODIEN
			where maKH = @maKH 
				AND maThang = @maThang;
		return @CSC;
	end;
go

create function getCSM(@maKH char(5), @maThang char(7))
returns int
as 
	begin
		declare @CSM int;
		select @CSM = chisomoi from CHISODIEN
			where maKH = @maKH 
				AND maThang = @maThang;
		return @CSM;
	end;
go

create function getLDTT(@maKH char(5), @maThang char(7)) --get luong dien tieu thu
returns int
as 
	begin
		return dbo.getcsm(@maKH, @maThang) - dbo.getcsc(@maKH, @maThang);
	end;
go

create function getMoney(@maKH char(5), @loaiDien nvarchar(20), @maThang char(7)) --tra ve so tien cua hoadon
returns money
as
	begin 
		declare @tien money;
		if(@loaiDien = N'Sinh hoạt')
			select @tien = (select dbo.getLDTT(@maKH, @maThang) * 2014);
		if(@loaiDien = N'Sản xuất')
			select @tien = (select dbo.getLDTT(@maKH, @maThang)) * 1611;
		if(@loaiDien = N'Kinh doanh')
			select @tien = (select dbo.getLDTT(@maKH, @maThang)) * 2442;
		if(@loaiDien = N'Hành chính')
			select @tien = (select dbo.getLDTT(@maKH, @maThang)) * 1659;
		return @tien;
	end;
go
	
create proc updateHoaDon(@maKH char(5), @maThang char(7)) --TAO THU TUC UPDATE DU LIEU
as 
	update hoadon
	set ldtt = (select dbo.getLDTT(@maKH, @maThang)),
		tien = (select dbo.getMoney(@maKH,(select loaiDien from HOTIEUTHU where maKH = @maKH),@maThang))
	where maKH = @maKH
go
