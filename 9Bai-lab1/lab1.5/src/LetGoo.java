public class LetGoo {
    public static void main(String[] args){
        InvoiceItem i1 = new InvoiceItem("A101", "Pen Red", 888, 0.08);
        System.out.println(i1);

        i1.setQty(999);
        i1.setUnitPrice(0.99);
        System.out.println(i1);

        System.out.println("ID = "+i1.getId());
        System.out.println("Desc = "+i1.getDesc());
        System.out.println("Qty = "+i1.getQty());
        System.out.println("UnitPrice = "+i1.getUnitPrice());
        System.out.println("Total = "+i1.getTotal());

    }
}
