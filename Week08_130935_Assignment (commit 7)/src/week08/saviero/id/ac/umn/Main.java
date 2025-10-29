package week08.saviero.id.ac.umn;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
	static ArrayList<Item> ListOfItems = new ArrayList<Item>();
	static ArrayList<Payment> ListOfPayments = new ArrayList<Payment>();
	static Scanner s = new Scanner(System.in);
	
	public static void seedData() {
		ListOfItems.add(new Item("Kulkas", "Elektronik", 4800000));
		ListOfItems.add(new Item("TV", "Elektronik", 12800000));
		ListOfItems.add(new Item("Laptop", "Komputer", 6000000));
		ListOfItems.add(new Item("PC", "Komputer", 12000000));
	}
	
	public static void printItem(Item item) {
		System.out.println("Nama	: " + item.getName());
		System.out.println("tipe	: " + item.getType());
		System.out.println("Harga	: " + item.getPrice());
	}
	
	public static void printPayment(Payment p) {
		System.out.println("Nama Barang		: " + p.getItemName());
		System.out.println("Jenis Pembayaran: " + (p instanceof Cash ? "Cash" : "Credit"));
		System.out.println("Status			: " + p.getStatus());
		System.out.println("Sisa Pembayaran	: " + p.getRemainingAmount());
		System.out.println("--------------------------------");
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int opt = 0;
		int id = 0;
		seedData();
		do {
			System.out.println("---Program Toko Elektonik---");
			System.out.println("1. Pesan Barang");
			System.out.println("2. Lihat Pesanan");
			System.out.println("0. Keluar");
			System.out.println("Pilih : ");
			opt = s.nextInt();
			if(opt == 1) {
				System.out.println("---- Daftar Barang ----");
				for(int i = 0; i < ListOfItems.size(); i++) {
					System.out.println("No : " + (i+1));
					printItem(ListOfItems.get(i));
					System.out.println("------------------");
				}
				System.out.print("Pilih nomor barang (0 untuk batal): ");
				int pilih = s.nextInt();
				
				if(pilih == 0) {
					continue;
				}
				if(pilih < 1 || pilih > ListOfItems.size()) {
					System.out.println("Nomor barang tidak valid!");
					continue;
				}
				
				Item chosenItem = ListOfItems.get(pilih - 1);
				System.out.println("Anda memilih: " + chosenItem.getName());
				System.out.println("1. Bayar Cash");
				System.out.println("2. Bayar Kredit");
				System.out.print("Pilih metode pembayaran: ");
				int metode = s.nextInt();
				
				if(metode == 1) {
					Cash cashPayment = new Cash(chosenItem);
					int bayar = cashPayment.pay();
					System.out.println("Total yang dibayar: Rp" + bayar);
					ListOfPayments.add(cashPayment);
					System.out.println("Pembayaran cash berhasil!");
					
				} else if(metode == 2) {
					System.out.print("Masukkan jumlah cicilan (misal 3): ");
					int maxInstall = s.nextInt();
					
					Credit creditPayment = new Credit(chosenItem, maxInstall);
					System.out.println("\n-- Proses Pembayaran Kredit --");
					while(!creditPayment.isPaidOff()) {
						int bayarCicilan = creditPayment.pay();
						System.out.println("Cicilan ke-" + creditPayment.getInstallment() + " dibayar: Rp" + bayarCicilan);
						System.out.println("Sisa pembayaran: Rp" + creditPayment.getRemainingAmount());
						System.out.println();
					}
					System.out.println("Semua cicilan telah lunas! ✅");
					ListOfPayments.add(creditPayment);
					
				} else {
					System.out.println("Metode pembayaran tidak valid!");
				}
				
			} else if(opt == 2) {
				System.out.println("---- Daftar Pesanan ----");
				if(ListOfPayments.isEmpty()) {
					System.out.println("Belum ada pesanan yang dilakukan.");
					} else {
						for(Payment p : ListOfPayments) {
							printPayment(p);
							}
						}
			}  else if(opt == 0) {
				System.out.println("Terima Kasih");
			} else {
				System.out.println("Salah Input");
			}
		} while (opt != 0);
	}
}
